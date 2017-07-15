object json {
  val f: String => String = {
    case "ping" => "pong"
  }

  f("ping")

  val f2: PartialFunction[String, String] = {
    case "ping" => "pong"
  }

  f2.isDefinedAt("ping")
  f2.isDefinedAt("pong")

  // std lib implementation
  trait PartialFunction[-A, +R] extends Function1[-A, +R] {
    def apply(x: A): R
    def isDefinedAt(x: A): Boolean
  }
}