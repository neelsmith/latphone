package edu.holycross.shot.latin
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

  it should "implement the MidOrthography's tokenizeString function" in pending

}
