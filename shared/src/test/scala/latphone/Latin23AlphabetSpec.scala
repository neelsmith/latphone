package edu.holycross.shot.latin
import org.scalatest.FlatSpec



class Latin23AlphabetSpec extends FlatSpec {

  "The Latin23Alphabet object"  should "have an ordered list of 23 alphabetic characters" in {
    assert(Latin23Alphabet.alphabetString.size == 23)
  }

  it should "recognize vowels" in {
    assert (Latin23Alphabet.vowel("a"))
    assert (Latin23Alphabet.vowel("A"))
    assert (Latin23Alphabet.vowel("b") == false)
    assert (Latin23Alphabet.vowel("B") == false)
    assert (Latin23Alphabet.vowel("i") == false)
    assert (Latin23Alphabet.vowel("I") == false)
  }

  it should "recognize consonants" in {
    assert (Latin23Alphabet.consonant("b"))
    assert (Latin23Alphabet.consonant("B"))
    assert (Latin23Alphabet.consonant("e") == false)
    assert (Latin23Alphabet.consonant("E") == false)
  }

  it should "recognize semivowels" in {
    assert (Latin23Alphabet.semivowel("i"))
    assert (Latin23Alphabet.semivowel("I"))
    assert (Latin23Alphabet.semivowel("e") == false)
    assert (Latin23Alphabet.semivowel("E") == false)
  }

  it should "have a rational override of default toString " in {
    assert (s"${Latin23Alphabet}" == "Latin alphabet with 23 alphabetic characters.")
  }

  it should "break syllables on CVC pattern" in {
    val domus = "domus"
    val actual = Latin23Alphabet.syllabify(domus)
    val expected = Vector("do", "mus")

    assert( actual == expected)

    val amicus = "amicus"
    val amicusActual = Latin23Alphabet.syllabify(amicus)
    val amicusExpected = Vector("a", "mi", "cus")

    assert( amicusActual == amicusExpected)

  }

  it should "correctly break syllables on consonant clusters" in {
  }
}
