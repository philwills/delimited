package com.github.philwills.delimited

import org.scalatest.FunSpec
import org.scalatest.matchers._
import org.joda.time.DateTime

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

    describe("writing a Redshift compatible CSV") {
      import RedshiftFormats._
      CSV.string(Seq(
        (1,
          """two or
            |maybe "three"
            |""".stripMargin, Some(new DateTime(2013, 3, 20, 9, 27, 3))),
        (4,"five",None)
      )) should be (
        """1,"two or\nmaybe \"three\"\n",03.20.2013 09:27:03.000
4,"five",""")
    }
  }
}
