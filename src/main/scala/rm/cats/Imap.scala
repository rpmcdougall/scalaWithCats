package rm.cats

import rm.cats.RunPrintableBoxContramap.Box
trait Codec[A] {
  def encode(value: A): String
  def decode(value: String): A
  def imap[B](dec: A => B, enc: B => A): Codec[B] = {
    val self = this
    new Codec[B] {
      def encode(value: B): String =
        self.encode(enc(value))

      def decode(value: String): B =
        dec(self.decode(value))
    }
  }
}

object RunImap extends App {

  implicit val stringCodec: Codec[String] =
    new Codec[String] {
      def encode(value: String): String = value
      def decode(value: String): String = value
    }

  implicit val doubleCodec: Codec[Double] = {
    stringCodec.imap[Double](_.toDouble, _.toString)
  }

  implicit val intCodec: Codec[Int] =
    stringCodec.imap(_.toInt, _.toString)

  implicit val booleanCodec: Codec[Boolean] =
    stringCodec.imap(_.toBoolean, _.toString)

  implicit def boxCodec[A](implicit c: Codec[A]): Codec[Box[A]] = {
    c.imap[Box[A]](Box(_), _.value)
  }

  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)

  def decode[A](value: String)(implicit c: Codec[A]): A =
    c.decode(value)


  println(encode(123.4))
  // res0: String = 123.4

  println(decode[Double]("123.4"))
  // res1: Double = 123.4

  println(encode(Box(123.4)))
  // res2: String = 123.4

  println(decode[Box[Double]]("123.4"))
  // res3: Box[Double] = Box(123.4)
}


