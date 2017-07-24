object loops {

  @annotation.tailrec
  def WHILE(condition: => Boolean)(command: => Unit): Unit =
    if (condition) {
      command
      WHILE(condition)(command)
    } else ()

  @annotation.tailrec
  def REPEAT(command: => Unit)(condition: => Boolean): Unit = {
    command
    if (condition) ()
    else REPEAT(command)(condition)
  }

  // Scala `for` loop similar to Java's extended `for` loop:
  for (i <- 1 until 3) { System.out.print(i + " ") }

  for (i <- 1 until 3; j <- "abc") println(s"$i $j")
  // Translates to:
  (1 until 3) foreach (i => "abc" foreach (j => println(s"$i $j")))
}