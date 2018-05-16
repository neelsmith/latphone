package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinAlphabetSpec extends FlatSpec {


  "A LatinAlphabet" should "recognize numeric characters as valid" in {
    val fourteen = s"${LatinNumerics.ten}${LatinNumerics.four}"

    assert (Latin23Alphabet.numeric(fourteen))
  }

  it should "distinguish alphabetic characters with similar looking glyphs" in {
    val alphabetic = "IV"
    assert(Latin23Alphabet.numeric(alphabetic) == false)
  }


}
