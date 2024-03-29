package zero.actors

import akka.actor._
import akka.pattern.{ask, pipe}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor.Props
import akka.util.Timeout

object Coachella {
  def props(implicit timeout: Timeout) = Props(new Coachella)

  case class CreateEvent(name: String, tickets: Int) // message to create an event
  case class GetEvent(name: String) // message to get an event
  case object GetEvents // message to request all events
  case class GetTickets(event: String, tickets: Int) // message to get tickets for an event
  case class CancelEvent(name: String) // message to cancel an event

  case class Event(name: String, tickets: Int) // message describing the event
  case class Events(events: Vector[Event]) // message describing a list of events

  sealed trait EventResponse // message response to create an event
  case class EventCreated(event: Event) extends EventResponse // message to indicate the event was created
  case object EventExists extends EventResponse // message to indicate that the event already exists
}

class Coachella(implicit timeout: Timeout) extends Actor {
  import Coachella._

  //  TicketSeller child
  def createTicketSeller(name: String): ActorRef = {
    context.actorOf(TicketSeller.props(name), name)
  }

  def receive: PartialFunction[Any, Unit] = {
    case CreateEvent(name, tickets) ⇒
      def create(): Unit = {
        //        creates the ticket seller
        val eventTickets = createTicketSeller(name)
        //        builds a list of numbered tickets
        val newTickets = (1 to tickets).map { ticketId ⇒
          TicketSeller.Ticket(ticketId)
        }.toVector
        //        sends the tickets to the TicketSeller
        eventTickets ! TicketSeller.Add(newTickets)
        //        creates an event and responds with EventCreated
        sender() ! EventCreated(Event(name, tickets))
      }
      //      If event exists it responds with EventExists
      context.child(name).fold(create())(_ ⇒ sender() ! EventExists)


    case GetTickets(event, tickets) ⇒
      //      sends an empty Tickets message if the ticket seller couldn't be found
      def notFound(): Unit = sender() ! TicketSeller.Tickets(event)
      //      buys from the found TicketSeller
      def buy(child: ActorRef): Unit = {
        child.forward(TicketSeller.Buy(tickets))
      }
      //      executes notFound or buys with the found TicketSeller
      context.child(event).fold(notFound())(buy)


    case GetEvent(event) =>
      def notFound() = sender() ! None
      def getEvent(child: ActorRef) = child forward TicketSeller.GetEvent
      context.child(event).fold(notFound())(getEvent)


    case GetEvents ⇒
      def getEvents = {
        context.children.map { child ⇒
          //          asks all TicketSellers about the events they are selling for
          self.ask(GetEvent(child.path.name)).mapTo[Option[Event]]
        }
      }
      def convertToEvents(f: Future[Iterable[Option[Event]]]): Future[Events] = {
        f.map(_.flatten).map(l ⇒ Events(l.toVector))
      }
      pipe(convertToEvents(Future.sequence(getEvents))) to sender()


    case CancelEvent(event) ⇒
      def notFound(): Unit = sender() ! None
      //      ActorRef carries the message that should be sent to an Actor
      //      Here we'll forward the message to the TicketSeller actor that an event was canceled
      def cancelEvent(child: ActorRef): Unit = child forward TicketSeller.Cancel
      context.child(event).fold(notFound())(cancelEvent)
  }

}
