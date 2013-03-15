package com.github.philwills.delimited

import org.scalatest.FunSpec
import org.scalatest.matchers._

class FieldWriterTest extends FunSpec with ShouldMatchers {
  describe("Field Writer") {
    describe("String field writing") {
      it("writes simple strings as is") {
        FieldWriter.write("foo").get should be ("foo")
      }
      it("can escape as per rfc4180") {
        import RFC4180._
        FieldWriter.write("""
This is a
"mess"
""").get should be (""""
This is a
""mess""
"""")
      }
      it("can use backslash escaping") {
        import BackslashEscaped._
        FieldWriter.write("""So,
this \ is "awkward"""").get should be (
          """"So,\nthis \\ is \"awkward\""""".stripMargin
        )
      }
    }

    describe("Option writing") {
      it("should write out the contents of some option") {
        FieldWriter.write(Some("foo")).get should be ("foo")
      }

      it("should write nothing where there's nothing to write") {
        FieldWriter.write(Option.empty[String]) should be (None)
      }

      it("should handle a raw None") {
        FieldWriter.write(None) should be (None)
      }
    }

    describe("Number writing") {
      it("should write integral numbers out simply") {
        FieldWriter.write(5).get should be ("5")
      }
    }
  }
}