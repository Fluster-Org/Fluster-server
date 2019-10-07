package org.fluster.server.Utils

import cats.effect.{ContextShift, Sync}
import org.http4s.{EntityBody, HttpRoutes}
import zio.{IO, Task}
import tapir.Endpoint
import tapir.server.http4s.Http4sServerOptions
import tapir.server.ServerEndpoint

object ZioTapir {
  implicit class ZioEndpoint[I, E, O](e: Endpoint[I, E, O, EntityBody[Task]]) {
    def toZioRoutes(
      logic: I => IO[E, O]
    )(
      implicit serverOptions: Http4sServerOptions[Task],
      fs:                     Sync[Task],
      fcs:                    ContextShift[Task]
    ): HttpRoutes[Task] = {
      import tapir.server.http4s._

      e.toRoutes(i => logic(i).either)
    }

    def zioServerLogic(logic: I => IO[E, O]): ServerEndpoint[I, E, O, EntityBody[Task], Task] =
      ServerEndpoint(e, logic(_).either)
  }
}
