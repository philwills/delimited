Delimited
=========

Sometimes we don't get to work with efficient binary data interchange formats, or even reasonably well-defined text
formats like JSON. Sometimes we have to use clunky delimited text formats for which standards
(http://tools.ietf.org/rfc/rfc4180.txt) might exist, but are frequently flouted.

Delimited aims to make construcring such files from Scala less painful.

A quick example
---------------

```scala
import com.github.philwills.delimited.CSV

CSV.string(Seq(
	(1,"two",3),
	(4,"five",6)
))
```

Not terribly impressive. How about

```scala
import com.github.philwills.delimited.CSV

CSV.string(Seq(
	(1, Some("two"), 3),
  (4, None, 6)
))
```

well that's at least doing more than calling toString on everything.

How about conforming to the peculiarities of Amazon's Redshift service:

```scala
import com.github.philwills.delimited._
import RedshiftFormats._

import org.joda.time.DateTime

CSV.string(Seq(
    (1,"""two or
maybe "three"
""", Some(new DateTime(2013, 3, 20, 9, 27, 3))),
    (4,"five",None)
   ))
```
