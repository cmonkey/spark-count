package com.sparkcount.raft

import com.sparkcount.raft.Rfat._

import scala.util.Random

class PendingRequest(val id: RequestId, val index: Index, var nSucceded: Int, var nFailed: Int)

class LeaderState[T](peer: Peer[T]){

}

case object State extends Enumeration{
  val FOLLOWER, CANDIDATE, LEADER = Value
}
abstract  class Peer[T](val id: Id,
                        val config: Config,
                        val timeoutSeed: Duration,
                        val random: Random = Random) extends Runnable with LogRepository[T] with  Broker with Logging with AutoCloseable{

  if(!config.peers.contains(id))
    throw new IllegalStateException("peer " + id + " not in config " + config)

  private[raft] val leaderTimeout = timeoutSeed.copy(count = timeoutSeed.count/ 2)

  private[raft] var electionTimeout = nextElectionTimeout

  private[raft] val leaderState = new LeaderState(this)

  private[raft] val peerVoteResults: collection.mutable.Map[Id, Boolean] = collection.mutable.Map()

  private[raft] var commitIndex: Index = NO_TERM

  private[raft] var currentTerm: Term = NO_TERM
  private[raft] var votingTerm: Term = NO_TERM
  private[raft] var lastApplied: Entry = Entry(NO_TERM, NO_TERM)

  private[raft] var leader: Id = NO_LEADER
  private[raft] var votedFor: Id = NOT_VOTED

  @volatile private[raft] var isFinished = false

  private[raft] var state = State.CANDIDATE

  private[raft] def peerTick: Unit = {
    val timeout = if (state == State.LEADER) leaderTimeout else electionTimeout

    val opt: Option[AddressedPDU] = receive(timeout)

    if(opt.nonEmpty){
      val received: AddressedPDU = opt.get
      val (source, target, pdu) = (received.source, received.target, received.pdu)

      received match {
        case _ if target != id => send(AddressedPDU(id, source, InvalidPDU(InvalidPduState.INVALID_ID, currentTerm)))
        case _ if ! config.peers.exists(_ == source) => send(AddressedPDU(id, source, InvalidPDU(InvalidPduState.INVALID_SOURCE, currentTerm)))
        case _ => match {

          case ae: AppendEntries[T] => handleAppend(source, ae)
          case aa: AppendEntriesAck => handleAppendAck(source, aa)
          case rv: RequestVote => handleRequestVote(source, rv)
          case ra: RequestVoteAck => handleRequestAck(source, ra)
          case cr: ClientRequest[T] => handleClient(source, cr)
          case _ => throw new IllegalStateException("Peer " + this + " has no handler for pdu " + pdu)
        }
      }
    }else if (state != State.LEADER) callElection
    if (state == State.LEADER) leaderTick
  }

  def run(): Unit = {
    log.info("peer " + toString + " starting")
    while(!isFinished) peerTick
  }

  def addressedPDU(pdu: PDU, target: Id) : AddressedPDU = AddressedPDU(id, target, pdu)

  def handleAppend(source: Id, pdu: AppendEntries[T]): Unit = {

    lazy val ensureFollower = () = {
      if(source != leader || state != State.FOLLOWER){
        descendToFollower(pdu.term, source)
      }
    }

    val appendState = pdu match {
      case _ if pdu.term == currentTerm => AppendState.TERM_NOT_CURRENT
      case _ if lastApplied != pdu.previous => {
        ensureFollower()
        AppendState.MISSING_ENTRIES
      }
      case _ => {
        ensureFollower()
        if(pdu.entries.nonEmpty){
          putEntries(pdu.entries)
          lastApplied = pdu.entries.last.id
          commitIndex = Math.max(commitIndex, pdu.commitedIndex)
        }

        AppendState.SUCCESS
      }
    }
    snedladdressPDU(AppendEntriesAck(currentTerm, lastApplied, commitIndex, leaderTimeout), source)
  }

}
