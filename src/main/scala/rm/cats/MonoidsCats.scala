package rm.cats

import cats._
import cats.implicits._


object RunMonoidCats extends App {
  //Ex 2.5.4
//  def add(items: List[Int]): Int =
//    items.foldLeft(Monoid[Int].empty)(_ |+| _)

  println(add(List(1, 2, 3)))

  def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldLeft(monoid.empty)(_ |+| _)

  println(add(List(Some(1), None, Some(2), None, Some(3))))

  case class Order(totalCost: Double, quantity: Double)
  implicit val monoid: Monoid[Order] = new Monoid[Order] {
    def combine(o1: Order, o2: Order) =
      Order(
        o1.totalCost + o2.totalCost,
        o1.quantity + o2.quantity
      )

    def empty = Order(0, 0)
  }

   val o1 = Order(20.00, 40.00)
   val o2 = Order(22.00, 22.00)
   println(Monoid[Order].combine(o1, o2))

}
