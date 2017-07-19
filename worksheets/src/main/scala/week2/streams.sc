object streams {
  val xs = Stream.cons(1, Stream.cons(2, Stream.empty)) // Can use like a factory `Stream(1, 2, 3)`

  (1 to 1000).toStream

  def streamRange(lo: Int, hi: Int): Stream[Int] =
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))

  def listRange(lo: Int, hi: Int): List[Int] =
    if (lo >= hi) Nil
    else lo :: listRange(lo + 1, hi)

  // x #:: xs == Stream.cons(x, xs)

  trait Stream[+A] extends Seq[A] {
    def isEmpty: Boolean
    def head: A
    def tail: Stream[A]
  }

  object Stream {
    def cons[T](hd: T, tl: => Stream[T]) = new Stream[T] {
      override def isEmpty: Boolean = false

      override def head: T = hd

      override def tail: Stream[T] = tail
    }

    val empty = new Stream[Nothing] {
      override def isEmpty: Boolean = true

      override def head: Nothing = throw new NoSuchElementException("empty.head")

      override def tail: Seq[Nothing] = throw new NoSuchElementException("empty.tail")
    }
  }
}