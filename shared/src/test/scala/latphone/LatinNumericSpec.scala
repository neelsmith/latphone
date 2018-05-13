package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinNumericSpec extends FlatSpec {


  val fourteen = "\u2169\u2163"
  val forty  = "\u2169\u216C"
  val ninety = "\u2169\u216D"

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

  it should "convert individual numeric chars to integer values"  in {
    val ten = '\u2169'
    assert(Latin23Alphabet.numericToInt(ten) == 10)
  }

  it should "convert strings of numeric chars to integer values" in {
    assert(Latin23Alphabet.numericToInt(fourteen) == 14)
  }
  it should "recognize subtractrive patterns for 40 and 90" in {
    assert(Latin23Alphabet.numericToInt(forty) == 40)
    assert(Latin23Alphabet.numericToInt(ninety) == 90)
  }


  "A LatinString"  should "accept numeric characters in its constructor" in {
    val ls = LatinString(fourteen, Latin23Alphabet)
    assert( ls.size == 2)
  }

}
