package com.github.philwills.delimited

object CSV {
  def string[T](source: Seq[T])(implicit rowWriter: RowWritable[T], rowSeparator: RowSeparator) = {
    source map (RowWriter.write(_)) mkString (rowSeparator.separator)
  }
}

trait RowSeparator {
  def separator: String
}

object RowSeparator {
  implicit object NewLineSeparator extends RowSeparator {
    val separator = "\n"
  }
}
