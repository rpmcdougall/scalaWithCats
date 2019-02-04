package rm.cats

import cats._
import cats.implicits._



  object RunAndMonoid extends App {

    implicit val booleanAndMonoid: Monoid[Boolean] =
      new Monoid[Boolean] {
        def combine(a: Boolean, b: Boolean) = a && b

        def empty = true
      }
    assert(Monoid.combine(true, false) == false)
    assert(Monoid.combine(false, true) == false)
    assert(Monoid.combine(true,true) == true)
    assert(Monoid.combine(false, false) == false)
  }

object RunOrMonoid extends App{
  implicit val booleanOrMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def combine(a: Boolean, b: Boolean) = a || b

      def empty = false
    }

  assert(Monoid.combine(true, false) == true)
  assert(Monoid.combine(false, true) == true)
  assert(Monoid.combine(true,true) == true)
  assert(Monoid.combine(false, false) == false)
}

  object RunEitherMonoid extends App {
    implicit val booleanEitherMonoid: Monoid[Boolean] =
      new Monoid[Boolean] {
        def combine(a: Boolean, b: Boolean) =
          (a && !b) || (!a && b)

        def empty = false
      }

    assert(Monoid.combine(true, false) == true)
    assert(Monoid.combine(false, true) == true)
    assert(Monoid.combine(true,true) == false)
    assert(Monoid.combine(false, false) == false)
  }

  object RunXnorMonoid extends App {
    implicit val booleanXnorMonoid: Monoid[Boolean] =
      new Monoid[Boolean] {
        def combine(a: Boolean, b: Boolean) =
          (!a || b) && (a || !b)

        def empty = true
      }
    assert(Monoid.combine(true, false) == false)
    assert(Monoid.combine(false, true) == false)
    assert(Monoid.combine(true,true) == true)
    assert(Monoid.combine(false, false) == true)
  }





