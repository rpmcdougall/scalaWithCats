package rm.cats

trait PrintableC[A] {
  self =>
  def format(value: A): String

  def contramap[B](func: B => A): PrintableC[B] =
    new PrintableC[B] {
      def format(value: B): String =
        self.format(func(value))
    }

  def format[A](value: A)(implicit p: PrintableC[A]): String =
    p.format(value)
}

object RunPrintableWithContramap extends App {

  implicit val stringPrintable: PrintableC[String] =
    new PrintableC[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: PrintableC[Boolean] =
    new PrintableC[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  println(booleanPrintable.format(true))
  println(stringPrintable.format("hello"))

}

object RunPrintableBoxContramap extends App {


  final case class Box[A](value: A)

  implicit val stringPrintable: PrintableC[String] =
    new PrintableC[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit val booleanPrintable: PrintableC[Boolean] =
    new PrintableC[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  implicit def boxPrintable[A](implicit p: PrintableC[A]) ={
    p.contramap[Box[A]](_.value)
  }

  println(boxPrintable[String].format(Box("hello")))
  println(boxPrintable[Boolean].format(Box(true)))


}