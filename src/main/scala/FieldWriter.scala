package com.github.philwills.delimited

object FieldWriter {
  def write[T](field: T)(implicit writeable: FieldWritable[T]) = {
    writeable.write(field)
  }
}


