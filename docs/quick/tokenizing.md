---
title: Tokenizing citable text
layout: page
---

In addition to the `latphone` library, you'll need the `cite` and `ohco2` libraries from the CITE architecture:

```scala
import edu.holycross.shot.latin._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
```

You could acquire a `CitableNode` of text in many ways (probably from a larger citable corpus), but for simplicity's sake in this tutorial, we'll just build a text repository from a string in CEX format, and save its corpus.

```scala
val livyTwoEightFour = """#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:omar:stoa0179.stoa001.omar:#book,chapter,section#Livy#History of Rome#omar edition##true#lat
#!ctsdata
urn:cts:omar:stoa0179.stoa001.omar:2.8.4#creatus Sp. Lucretius consul, qui magno natu non sufficientibus iam viribus ad consularia munera obeunda intra paucos dies moritur. suffectus in Lucreti locum M. Horatius Pulvillus.
"""
val corpus = TextRepository(livyTwoEightFour).corpus
```


The only citable node in this tiny corpus book 2, chapter 8, section 4 of Livy, so we'll grab the first node in the corpus, and convert it to a series of tokens.

```scala
val livy2_8_4 = corpus.nodes(0)
val tokens = LatinTextReader.nodeToTokens(livy2_8_4, Latin24Alphabet)

assert(tokens.size == 29)
tokens.mkString("\n")
```

Maybe you want to strip out punctuation:  easy in Scala.

```scala
val depunctuated = tokens.filterNot(_.category == Punctuation)
assert(depunctuated.size == 26)
```
