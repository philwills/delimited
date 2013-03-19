package com.github.philwills.delimited

import org.scalatest.FunSpec
import org.scalatest.matchers._

class CSVWriterTest extends FunSpec with ShouldMatchers {
  describe("Writing a CSV") {
    describe("Simple CSV writing") {
      CSV.string(Seq(
        (1,"two",3),
        (4,"five",6)
      )) should be (
"""1,"two",3
4,"five",6""")
    }
  }
}
