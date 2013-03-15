package com.github.philwills.delimited

object RowWriter {
  def asRow[T](row: T)(implicit writable: RowWritable[T]) = {
    writable.write(row)
  }
  def write[T](row: T, delimiter: String = ",")(implicit writable: RowWritable[T], noValue: NoValueRepresentation) = {
    writable.write(row).map(_.getOrElse(noValue.rep)).mkString(delimiter)
  }
}

trait NoValueRepresentation {
  def rep: String
}

object NoValueRepresentation {
  implicit object EmptyStringRepresentation extends NoValueRepresentation{
    def rep: String = ""
  }
}


