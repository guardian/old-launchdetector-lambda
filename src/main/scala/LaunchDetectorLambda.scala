import com.amazonaws.services.lambda.runtime.events.SNSEvent
import com.amazonaws.services.lambda.runtime
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord
import com.gu.contentatom.thrift.Atom
import com.gu.crier.model.event.v1._
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

import scala.collection.JavaConverters._

class LaunchDetectorLambda extends RequestHandler[SNSEvent, Unit] {
  override def handleRequest(incomingEvent:SNSEvent, context: Context): Unit = {
    println(s"Got incoming event: ${incomingEvent.toString}")
    if(incomingEvent.getRecords==null) throw new RuntimeException("Incoming event has no records! getRecords returned null.")

    println(s"Event has records: ${incomingEvent.getRecords}")
    println(s"Converted to scala: ${incomingEvent.getRecords.asScala.toList.toString}")

    val rawRecords: List[SNSRecord] = incomingEvent.getRecords.asScala.toList

    println(s"Processing ${rawRecords.size} records ...")
    CrierEventProcessor.process(rawRecords) { event=>
      event.itemType match {
        case ItemType.Atom=>
          event.payload.exists({
            case EventPayload.Atom(atom)=>
              AtomEventProcessor.process(atom, event.eventType)
            case _=>
              false
          })
        case _=>
          println("This event is for something other than an atom, not going to do anything.")
          false
      }
    }
  }
}
