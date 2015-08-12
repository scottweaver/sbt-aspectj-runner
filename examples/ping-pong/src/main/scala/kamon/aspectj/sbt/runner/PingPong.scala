package kamon.aspectj.sbt.runner

import akka.actor._
import kamon.Kamon

object PingPong extends App {
  Kamon.start()

  val system = ActorSystem()

  val pinger = system.actorOf(Props(new Actor {
    def receive: Actor.Receive = { case "pong" ⇒ sender ! "ping" }
  }), "pinger")

  val ponger = system.actorOf(Props(new Actor {
    def receive: Actor.Receive = { case "ping" ⇒ sender ! "pong" }
  }), "ponger")

  pinger.tell("pong", ponger)
}
