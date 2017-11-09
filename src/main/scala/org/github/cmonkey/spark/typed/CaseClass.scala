package org.github.cmonkey.spark.typed
case class Book(isbn: String)

case class Message(sender: String, recipient: String, body: String)

object CaseClass{
  def main(args: Array[String]): Unit = {

    val frankenstein = Book("978-0486282114")

    val message1 = Message("t@quebec.ca", "j@catalonia.es", "ca")
    println(message1.sender)
    //message1.sender = "t@on.ca"
    val message2 = Message("t@quebec.ca", "j@catalonia.es", "ca")

    val messageAreTheSame = message1 == message2

    println(messageAreTheSame)

    val message3 = message2.copy(sender = message2.recipient, recipient = "a@cn")
    println(s"$message3.sender, $message3.recipient , $message3.body")
  }
}
