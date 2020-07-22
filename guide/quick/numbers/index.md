---
title: Numeric tokens
layout: page
---


The `LatinNumerics` object can validate the syntax of a Latin numeric string.

```tut:silent
import edu.holycross.shot.latin._

assert(LatinNumerics.valid("ⅩⅣ"))

assert(LatinNumerics.valid("ⅣⅩ") == false)
```

It can also convert valid values to integers.


```tut:silent
assert (LatinNumerics.numericToInt("ⅩⅣ") ==  14)
```

Note that the numeric characters are drawn from the Unicode code points [summarized here](https://neelsmith.github.io/latphone/numerics/).  If you try to use non-numeric characters, it is an exception.

```tut
// True numeric characters:
assert(LatinNumerics.valid("ⅩⅣ"))

// Similar looking glyphs for alphabetic characters:
try {
  LatinNumerics.valid("XIV")
} catch {
  case t: Throwable => t
}
```
