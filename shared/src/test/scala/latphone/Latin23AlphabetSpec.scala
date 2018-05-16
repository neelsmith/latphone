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
    val sanctus = "sanctus"
    val actual =  Latin23Alphabet.syllabify(sanctus)
    val expected = Vector("sanc","tus")
      assert( actual == expected)
  }

  it should "correctly break syllables on mute+liquid combination" in {
    val publicus = "publicus"
    val actual =  Latin23Alphabet.syllabify(publicus)
    val expected = Vector("pu","bli","cus")
    assert( actual == expected)
  }
  it should "correctly break syllables on diphthong+vowel combination" in {
    val aeacus = "aeacus"
    val actual =  Latin23Alphabet.syllabify(aeacus)
    val expected = Vector("ae","a","cus")
    assert( actual == expected)
  }

  it should "correctly break syllables when a combines with vowel" in {
    val laocoon = "Laocoon"
    val actual =  Latin23Alphabet.syllabify(laocoon)
    val expected = Vector("la","o","co", "on")
    assert( actual == expected)
  }

  it should "correctly break syllables when e combines with vowel" in {

  }


  it should "correctly break syllables when i combines with vowel" in {
    val consilii = "consilii"
    val actual =  Latin23Alphabet.syllabify(consilii)
    val expected = Vector("con","si","li", "i")
    assert( actual == expected)
  }

  it should "correctly break syllables when o combines with vowel" in {
    val laocoon = "Laocoon"
    val actual =  Latin23Alphabet.syllabify(laocoon)
    val expected = Vector("la","o","co", "on")
    assert( actual == expected)
  }


  it should "correctly break syllables when u combines with vowel" in {
    val tonitrui = "tonitrui"
    val actual =  Latin23Alphabet.syllabify(tonitrui)
    val expected = Vector("to","ni","tru", "i")
    assert( actual == expected)
  }

  it should "correctly syllabify words with intial semivocalic i" in {
    val iustitia = "iustitia"
    val actual =  Latin23Alphabet.syllabify(iustitia)
    val expected = Vector("ius","ti","ti", "a")
    assert( actual == expected)

    val iacet = "iacet"
    val actual2 =  Latin23Alphabet.syllabify(iacet)
    val expected2 = Vector("ia","cet")
    assert( actual2 == expected2)
  }

  it should "correctly syllabify words with syllable-intial semivocalic i" in pending /*{
    // following closed syllable with liquid
    val coniugo = "coniugo"
    val actual =  Latin23Alphabet.syllabify(coniugo)
    val expected = Vector("con","iu","go")
    assert( actual == expected)
    // following open syllable
    val deieci = "deieci"
    val actual2 =  Latin23Alphabet.syllabify(deieci)
    val expected2 = Vector("de","ie","ci")
    assert( actual2 == expected2)
    // following closed syllable with stop
    val iustitiam = "iustitiam"
    val actual3 =  Latin23Alphabet.syllabify(iustitiam)
    val expected3 = Vector("ius","ti","ti","am")
    assert( actual3 == expected3)
  } */

  it should "protect qu combination in syllabifying" in {
    val quae = "quae"
    val actual =  Latin23Alphabet.syllabify(quae)
    val expected = Vector("quae")
    assert( actual == expected)

    val eloquentia = "eloquentia"
    val actual2 =  Latin23Alphabet.syllabify(eloquentia)
    val expected2 = Vector("e","lo","quen","ti","a")
    assert( actual2 == expected2)
  }

  it should "correctly syllabify words with intial semivocalic u" in {
    val uela = "uela"
    val actual =  Latin23Alphabet.syllabify(uela)
    val expected = Vector("ue","la")
    assert( actual == expected)
  }


  it should "correctly syllabify words with syllable-intial semivocalic u" in pending /* {
    val deueho = "deueho"
    val actual =  Latin23Alphabet.syllabify(deueho)
    val expected = Vector("de","ue","ho")
    assert( actual == expected)

    val servius = "Seruius"
    val actual2 =  Latin23Alphabet.syllabify(servius)
    val expected2 = Vector("ser","ui","us")
    assert( actual2 == expected2)
  } */


  // di-u-i   ae-u-i
  // obt-rec-ta-ti-o
  // Ini-ti-um
  // Ser-iius
}
