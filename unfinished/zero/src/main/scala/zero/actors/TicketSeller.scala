package zero.actors

import akka.actor.{Actor, PoisonPill}
import akka.actor.Props

object TicketSeller {

  def props(event: String) = Props(new TicketSeller(event))

  case class Add(tickets: Vector[Ticket]) // message to add tickets to the TicketSeller
  case class Buy(tickets: Int) // message to buy tickets from the TicketSeller
  case class Ticket(id: Int) // A ticket
  case class Tickets(event: String,
    entries: Vector[Ticket] = Vector.empty[Ticket]) // a list of tickets for an event
  case object GetEvent // a message containing the remaining tickets for an event
  case object Cancel // a message to cancel the event
}

class TicketSeller(event: String) extends Actor {
  import TicketSeller._
  //  list of tickets
  var tickets = Vector.empty[Ticket]

  def receive: PartialFunction[Any, Unit] = {
    // Adds the new tickets to the existing list of tickets when Tickets message is received
    case Add(newTickets) ⇒ tickets = tickets ++ newTickets

    case Buy(numberOfTickets) ⇒
      // Takes a number of tickets off the list
      val entries = tickets.take(numberOfTickets)

      if (entries.size >= numberOfTickets) {
        // if there are enough tickets available, responds with a Tickets message containing the tickets
        sender() ! Tickets(event, entries)
        tickets = tickets.drop(numberOfTickets)
        //   otherwise respond with an empty Tickets message
      } else sender() ! Tickets(event)
    // returns an event containing the number of tickets left when GetEvent is received
    case GetEvent ⇒ sender() ! Some(Coachella.Event(event, tickets.size))

    case Cancel ⇒ sender() ! Some(Coachella.Event(event, tickets.size))
      self ! PoisonPill
  }
}
