object monads {
  // Definition: data structures with `map` and `flatMap`

  trait M[T] {

    // commonly called `bind`
    def flatMap[U](f: T => M[U]): M[U]

    def unit(x: T): M[T]
  }


  // `List` is a monad with `unit(x) = List(x)`
  // `Set` is a monad with `unit(x) = Set(x)`
  // `Option` is a monad with `unit(x) = Some(x)`
  // `Generator` is a monad with `unit(x) = single(x)`

  // `flatMap` is an operation on each of these types,
  // whereas `unit` in Scala is different for each monad.

  // `map` can be defined for every monad as a combination of `flatMap` and `unit`:
  // `m map f == m flatMap (x => unit(f(x)))`
  // `m map f == m flatMap (f andThen unit)`

  // To qualify as a monad, a type has to satisfy 3 laws:

  // Associativity:
  // `(m flatMap f) flatMap g == m flatMap ((x => f(x) flatMap g)`

  // Left unit:
  // `unit(x) flatMap f == f(x)`

  // Right unit:
  // `m flatMap unit == m`

  abstract class Option[+T] {

    def flatMap[U](f: T => Option[U]): Option[U] = this match {
      case Some(x) => f(x)
      case None => None
    }
  }
}