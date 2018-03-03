package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class Latin23AlphabetSpec extends FlatSpec {

  "The Latin23Alphabet object"  should "have an ordered list of 23 alphabetic characters" in {
    assert(Latin23Alphabet.alphabetString.size == 23)
  }
}
