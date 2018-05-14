package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinNumericSpec extends FlatSpec {

  val fourteen = s"${LatinNumerics.ten}${LatinNumerics.four}"

  "The LatinNumerics object" should "accept an empty string as a valid sequence" in {
    assert(LatinNumerics.valid(""))
  }


  it should "accept values from ten to 20 when digits decrease in size" in  {
    assert(LatinNumerics.valid(fourteen))
  }

  it should "accept values decreasing in size" in  pending /*{
    val onehundred11 = s"${LatinNumerics.hundred}${LatinNumerics.ten}${LatinNumerics.one}"
    println("Testing " + onehundred11)
    assert(LatinNumerics.valid(onehundred11))

  }*/


  // Change to validate withg LatinNumerics!
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
    assert(LatinNumerics.numericToInt(ten) == 10)
  }

  it should "convert strings of numeric chars to integer values" in {
    assert(LatinNumerics.numericToInt(fourteen) == 14)
  }
  it should "recognize subtractive patterns for 40 and 90" in {
    assert(LatinNumerics.numericToInt(LatinNumerics.forty) == 40)
    assert(LatinNumerics.numericToInt(LatinNumerics.ninety) == 90)
  }

  it should "recognize subtractive patterns combined with other characrters" in {
    val fortyone = s"${LatinNumerics.forty}${LatinNumerics.one}"
    assert (LatinNumerics.numericToInt(fortyone) == 41)
  }


  "A LatinString"  should "accept numeric characters in its constructor" in {
    val ls = LatinString(fourteen, Latin23Alphabet)
    assert( ls.size == 2)
  }

}
