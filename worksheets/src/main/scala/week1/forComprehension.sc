object forComprehension {
  case class Book(title: String, authors: List[String])

  val books: Set[Book] = Set(
    Book(
      title = "Structure and Interpretation of Computer Programs",
      authors = List("Abelson, Harold", "Sussman, Gerald")
    ),
    Book(
      title = "Introduction to Functional Programming",
      authors = List("Bird, Richard", "Wadler, Phil")
    ),
    Book(
      title = "Effective Java",
      authors = List("Bloch, Joshua")
    ),
    Book(
      title = "Effective Java 2",
      authors = List("Bloch, Joshua")
    ),
    Book(
      title = "Java Puzzlers",
      authors = List("Bloch, Joshua", "Gafter, Neal")
    ),
    Book(
      title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")
    )
  )

  val bird1 = for (b <- books; a <- b.authors if a.startsWith("Bird")) yield b.title

  val bird2 = books.flatMap(b => for (a <- b.authors if a.startsWith("Bird")) yield b.title)

  val bird3 = books.flatMap(b => for (a <- b.authors.withFilter(a => a.startsWith("Bird"))) yield b.title)

  val bird4 = books.flatMap(b => b.authors.withFilter(a => a.startsWith("Bird")).map(y => y))

  val b1 = for (b <- books) yield b.title
  val b2 = books.map(b => b.title)

  for (b <- books if b.title.indexOf("Program") >= 0) yield b.title

  for {
    b1 <- books
    b2 <- books
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1

  // For-expressions and higher-order functions
  // Closely related to map, flatMap, and filter

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    for (x <- xs) yield f(x)

  def flatMap[T, U](xs: List[T], f: T => Iterable[U]): List[U] =
    for (x <- xs; y <- f(x)) yield y

  def filter[T](xs: List[T], p: T => Boolean): List[T] =
    for (x <-xs if p(x)) yield x


  // for (x <- e1) yield e2 == e1.map(x => e2)

  // for (x <- e1 if f; s) yield e2 == for (x <- e1.withFilter(x => f); s) yield e2
  // withFilter is a lazy filter applied following a call to `map` or `flatMap`

  // for (x <- e1; y <- e2; s) yield e3 == e1.flatMap(x => for (x <- e2; s) yield e3)


}