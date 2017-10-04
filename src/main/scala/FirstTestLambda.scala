import java.io.{InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

class FirstTestLambda extends RequestStreamHandler {
  override def handleRequest(input: InputStream, output: OutputStream, context: Context): Unit = {
    val inputData = scala.io.Source.fromInputStream(input).mkString
    println(inputData)
  }
}
