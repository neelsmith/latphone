package edu.holycross.shot.latin
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import org.scalatest.FlatSpec



class Latin23AlphabetMidTraitSpec extends FlatSpec {

  "The Latin23Alphabet object" should "implement the MidOrthography's orthography function" in {
    assert(Latin23Alphabet.orthography == "Latin alphabet with 23 alphabetic characters")
  }
  it should "implement the MidOrthography's validCP function" in {
    assert(Latin23Alphabet.validCP('a'.toInt))
    assert(Latin23Alphabet.validCP('β'.toInt) == false)
  }



  it should "implement the MidOrthography's tokenCategories function" in {
    val expected =     Vector(PraenomenToken, PunctuationToken, LexicalToken, NumericToken, InvalidToken)
    assert(Latin23Alphabet.tokenCategories == expected)
  }

  it should "implement the MidOrthography's tokenizeNode function" in {
    val n = CitableNode(CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:2.8.4"), "creatus Sp. Lucretius consul,")
    val tokens = Latin23Alphabet.tokenizeNode(n)

    val expectedNumber = 5
    assert(tokens.size == expectedNumber)
    assert(tokens.filter(_.tokenCategory.get == LexicalToken).size == 3)
    assert(tokens.filter(_.tokenCategory.get == PunctuationToken).size == 1)
    assert(tokens.filter(_.tokenCategory.get == PraenomenToken).size == 1)
  }

}
