package org.fluster.server

import org.http4s._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.syntax.kleisli._
import zio.{DefaultRuntime, IO, Task, UIO, ZIO}
import tapir._
import tapir.server.http4s._
import Utils.ZioTapir._
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.server.middleware.Logger
import org.http4s.implicits._
import cats.effect._
import cats.implicits._
import io.circe.generic.auto._
import tapir.json.circe._
import cats.effect._
import org.http4s.HttpRoutes
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.syntax.kleisli._
import tapir._
import tapir.server.http4s._
import cats.implicits._
import cats.effect.{ContextShift, IO => CIO}

import scala.concurrent.ExecutionContext

object MainZio extends App {

  implicit val Runtime:      DefaultRuntime = new DefaultRuntime {}
  implicit val ec:           ExecutionContext = Runtime.Platform.executor.asEC
  implicit val contextShift: ContextShift[CIO] = CIO.contextShift(ec)
  implicit val timer:        Timer[CIO] = CIO.timer(ec)

  case class Pet(
    species: String,
    url:     String
  )

  val PetEndpoint: Endpoint[Int, String, Pet, Nothing] =
    endpoint.get.in("pet" / path[Int]("petId")).errorOut(stringBody).out(jsonBody[Pet])

  val Service: HttpRoutes[Task] = PetEndpoint.toZioRoutes { petId =>
    if (petId == 35) {
      UIO(Pet("Tapirus terrestris", "https://en.wikipedia.org/wiki/Tapir"))
    } else {
      IO.fail("Unknown pet id")
    }
  }

  // Or, using server logic:

  val PetServerEndpoint = PetEndpoint.zioServerLogic { petId =>
    if (petId == 35) {
      UIO(Pet("Tapirus terrestris", "https://en.wikipedia.org/wiki/Tapir"))
    } else {
      IO.fail("Unknown pet id")
    }
  }

  val Service2: HttpRoutes[Task] = PetServerEndpoint.toRoutes

  //

//  import tapir.docs.openapi._
//  import tapir.openapi.circe.yaml._
//  val yaml = List(PetEndpoint).toOpenAPI("Our pets", "1.0").toYaml

  {

    val serve = BlazeServerBuilder[Task]
      .bindHttp(8080, "localhost")
      .withHttpApp(
        Logger.httpApp(true, true)(
          Router("/" -> Service /*, "/docs" -> new SwaggerHttp4s(yaml).routes[Task]*/ ).orNotFound
        )
      )
      .serve
      .compile
      .drain

    Runtime.unsafeRun(serve)
  }
}
