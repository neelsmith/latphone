package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinStringSpec extends FlatSpec {

  "A LatinString"  should "recognize accept valid characters" in {
    val ls = LatinString("quo usque tandem abutere patientia nostra?", Latin23Alphabet)
    assert(true)
  }
  it should "reject invalid characters" in {
    try {
      val ls = LatinString("μῆνιν ἄειδε", Latin23Alphabet)
      fail("Should not have made LatinString!")
    } catch {
      case iae: IllegalArgumentException => assert(iae.toString == "java.lang.IllegalArgumentException: requirement failed: μῆνιν ἄειδε is not a valid Latin string the Latin alphabet with 23 alphabetic characters.")
      case t: Throwable =>fail("Should have  found IllegalArgumentException but got " + t)
    }
  }

  it should "syllabify itself using its alphabet" in {
    val ls = LatinString("quo usque tandem abutere patientia nostra?", Latin23Alphabet)
    println(ls.syllabify.map(_.mkString("-")).mkString("\n"))
  }
}
