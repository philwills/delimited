package com.github.philwills.delimited

import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import java.sql.Timestamp

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
  implicit object NoneWritable extends FieldWritable[None.type] {
    def write(field: None.type) = None
  }
  implicit object AsIsStringFieldWritable extends FieldWritable[String] {
    def write(field: String) = Some(field)
  }
  implicit object ISO8601DateTimeFieldWritable extends FieldWritable[DateTime] {
    def write(field: DateTime) = Some(field.toString(ISODateTimeFormat.dateTime()))
  }
}
object RFC4180 {
  implicit object StringFieldWritable extends FieldWritable[String] {
    def write(field: String) = Some('"' + field.replaceAllLiterally("\"", "\"\"") + '"')
  }
}
object BackslashEscaped extends BackslashEscaped

trait BackslashEscaped {
  implicit object StringFieldWritable extends FieldWritable[String] {
    def write(field: String) = Some('"' + field
      .replaceAllLiterally("""\""","""\\""")
      .replaceAllLiterally("\n", """\n""")
      .replaceAllLiterally(""""""", """\"""")
    + '"')
  }
}

object RedshiftTimestampFormat extends RedshiftTimestampFormat

trait RedshiftTimestampFormat {
  implicit object RedshiftTimeStampFieldWritable extends FieldWritable[DateTime] {
    def write(field: DateTime) = Some(field.toString("MM.dd.YYYY HH:mm:ss.SSS"))
  }
}

