package com.github.philwills.delimited

import org.scalatest.FunSpec
import org.scalatest.matchers._

class RowWriterTest extends FunSpec with ShouldMatchers {
  describe("Row Writer") {
    describe("Tuple writing") {
      it("provides a sequence of contents") {
        RowWriter.asRow(("foo", Some("bar"))) should be (Seq(Some("foo"), Some("bar")))
        RowWriter.asRow((1, None, Some("bar"))) should be (Seq(Some("1"), None, Some("bar")))
      }
      it("is written out in an appropriately delimited way") {
        RowWriter.write(("foo", None, 6)) should be ("foo,,6")
      }
    }

    describe("Sequence writing") {
      it("should write out elements of sequence") {
        RowWriter.asRow(Seq(1,2,3)) should be (Seq(Some("1"), Some("2"), Some("3")))
      }
    }
  }
}
