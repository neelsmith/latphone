---
title: Quick start
layout: page
---

Include the library:

```tut:silent
import edu.holycross.shot.latin._
```


Create a `LatinString` by associating a string value with a specific alphabet class.


Example:  the `Latin23Alphabet` alphabet uses 23 alphabetic characters (no "j" or "v").


```tut:silent
val latString = LatinString("conuocare", Latin23Alphabet)
assert(latString.s == "conuocare")
```

Syllabifying a `LatinString` creates a Vector of string values.  It's fun to use scala's handy collection methods as illustrated here.

```tut:silent
val syllables = latString.syllabify
val syllabicString = syllables.mkString("-")
assert(syllabicString == "con-uo-ca-re")
```
