package edu.holycross.shot.latin
import org.scalatest.FlatSpec

import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

class Latin23SyllableSpec extends FlatSpec {

  "The Latin23Syllable object" should "tokenize a citable node" in {
    val n = CitableNode(CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:2.8.4"), "creatus Sp. Lucretius consul,")
    val sylls = Latin23Syllable.tokenizeNode(n)
    val expectedSyllables = 10
    assert (sylls.size == expectedSyllables)
    val expectedFirst = "cre"
    assert( sylls(0).string == expectedFirst)

  }
}
