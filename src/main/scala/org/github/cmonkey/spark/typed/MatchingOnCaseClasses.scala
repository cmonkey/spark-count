package org.github.cmonkey.spark.typed
abstract class Notification

case class Email(sender: String, title: String, body: String) extends Notification
case class SMS(caller: String, message: String)  extends Notification
case class VoiceRecording(contactName: String, link: String) extends Notification

object MatchingOnCaseClasses{

  def showNotification(notifiction: Notification): String = {
    notifiction match {
      case Email(email, title, _) => 
        s"You got an email from $email with title: $title"
      case SMS(number, message) => 
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) => 
        s"You received a Voice Recording from $name! Click the link : $link"
    }
  }

  def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
    notification match {
      case Email(email, _, _) if importantPeopleInfo.contains(email) =>
        "You got an email from special someone!"
      case SMS(number, _) if importantPeopleInfo.contains(number) =>
        "You got an SMS from special someone!"
      case other =>
        showNotification(other) // nothing special, delegate to our original showNotification function
    }
  }
  def main(args: Array[String]): Unit = {

    val someSms = SMS("1234", "are you there")

    val someVoiceRecording = VoiceRecording("tom", "voic")

    println(showNotification(someSms))
    println(showNotification(someVoiceRecording))

    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

    val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
    val importantSms = SMS("867-5309", "I'm here! Where are you?")

    println(showImportantNotification(someSms, importantPeopleInfo))
    println(showImportantNotification(someVoiceRecording, importantPeopleInfo))
    println(showImportantNotification(importantEmail, importantPeopleInfo))
    println(showImportantNotification(importantSms, importantPeopleInfo))
  }

}
