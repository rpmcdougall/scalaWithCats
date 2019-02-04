package rm.cats

import cats._
import cats.implicits._

object RunShowWithCats extends App{
  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
    )
  println(Cat("Winston", 5, "Magnifico").show)
}

