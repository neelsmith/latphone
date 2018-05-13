package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinNumericSpec extends FlatSpec {


  val fourteen = "\u2169\u2163"


  "A LatinAlphabet" should "recognize numeric characters as valid" in {
    assert (Latin23Alphabet.numeric(fourteen))
  }

  it should "distinguish alphabetic characters with similar looking glyphs" in {
    val alphabetic = "IV"
    try {
      Latin23Alphabet.numeric(alphabetic)
      fail(s"Should have identified ${alphabetic} as non-numeric.")
    } catch {
      case iae: java.lang.IllegalArgumentException => {
        val expected = "requirement failed: I is not a numeric character."
        assert (iae.getMessage == expected)
      }
      case _ : Throwable => fail("Should have thrown IllegalArgumentException for " + alphabetic)


    }

  }


  "A LatinString"  should "accept numeric characters in its constructor" in {
    val ls = LatinString(fourteen, Latin23Alphabet)
    assert( ls.size == 2)
  }

}
