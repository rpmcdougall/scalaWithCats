package rm.cats

trait Printable[A] {
  def format(fmt: A) : String
}

object PrintableInstances {
  implicit val stringPrintable = new Printable[String] {
    def format(input: String) = input
  }

  implicit val intPrintable = new Printable[Int] {
    def format(input: Int) = input.toString
  }
}

object Printable {
  def format[A](input: A)(implicit p: Printable[A]): String = {
    p.format(input)
  }

  def print[A](input: A)(implicit p: Printable[A]): Unit = {
    println(p.format(input))
  }
}

final case class Cat(name: String, age: Int, color: String)

object RunPrintable extends App {
  import PrintableInstances._
  val cat = Cat("Winston", 5, "Magnifico")

  implicit val printableCat = new Printable[Cat] {
    def format(cat: Cat): String = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }

  println(printableCat.format(cat))
}