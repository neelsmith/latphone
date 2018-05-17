package edu.holycross.shot.latin
import org.scalatest.FlatSpec

import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._


class LatinTokenizingSpec extends FlatSpec {

  "A LatinToken"   should "serialize itself to delimited text" in {
    val u = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.pr.1.1")

    CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.pr.1.1")
    val text = "facturae"
    val token = LatinToken(u, text, LexicalToken)

    val expected = s"${u}#${text}#LexicalToken"
    assert(token.delimitedText("#") == expected)
  }

  it should "serialize to CEX without further specification" in {
    val u = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.pr.1.1")
    val text = "facturae"
    val token = LatinToken(u, text, LexicalToken)

    val expected = s"${u}#${text}#LexicalToken"
    assert(token.cex == expected)
  }

}
