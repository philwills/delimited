package com.github.philwills.delimited

trait FieldWritable[-T] {
  def write(field: T): Field
}

object FieldWritable {
  implicit object IntFieldWritable extends FieldWritable[Int] {
    def write(field: Int) = Some(field.toString)
  }
  implicit def optionWritable[T](implicit writable: FieldWritable[T]) = new FieldWritable[Option[T]] {
    def write(field: Option[T]) = field flatMap (writable.write)
  }
  implicit object NoneWritable extends FieldWritable[None.type ] {
    def write(field: None.type) = None
  }
  implicit object AsIsStringFieldWritable extends FieldWritable[String] {
    def write(field: String) = Some(field)
  }

}
object RFC4180 {
  implicit object StringFieldWritable extends FieldWritable[String] {
    def write(field: String) = Some('"' + field.replaceAllLiterally("\"", "\"\"") + '"')
  }
}
object BackslashEscaped {
  implicit object StringFieldWritable extends FieldWritable[String] {
    def write(field: String) = Some('"' + field
      .replaceAllLiterally("""\""","""\\""")
      .replaceAllLiterally("\n", """\n""")
      .replaceAllLiterally(""""""", """\"""")
    + '"')
  }
}