package examples

import internal.Compartment
import examples.RobotExample.ActorView.DriveableRole
import examples.RobotExample.BehavioralView.ServiceRole
import examples.RobotExample.NavigationView.NavigationRole
import examples.RobotExample.SensorView.ObservingEnvironmentRole
import internal.util.Log.info

object RobotExample extends App {

  case class Robot(name: String)

  object BehavioralView extends Compartment {

    case class ServiceRole() {
      def move() {
        val name: String = +this name()
        val target: String = +this getTarget()
        val sensorValue: Int = +this readSensor()
        val actor: String = +this getActor()

        info(s"I am $name and moving to the $target with my $actor w.r.t. sensor value of $sensorValue.")
      }
    }

  }

  object NavigationView extends Compartment {

    case class NavigationRole() {
      def getTarget: String = "kitchen"
    }

  }

  object SensorView extends Compartment {

    case class ObservingEnvironmentRole() {
      def readSensor: Int = 100
    }

  }

  object ActorView extends Compartment {

    case class DriveableRole() {
      def getActor: String = "wheels"
    }

  }

  new Compartment {
    val myRobot = Robot("Pete") play ServiceRole() play NavigationRole() play ObservingEnvironmentRole() play DriveableRole()

    BehavioralView partOf this

    myRobot move()
  }
}
