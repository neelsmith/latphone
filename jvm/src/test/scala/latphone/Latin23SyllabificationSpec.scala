package edu.holycross.shot.latin
import org.scalatest.FlatSpec
import scala.io.Source


class Latin23SyllabificationSpec extends FlatSpec {

  "The Latin23Alphabet object"  should "syllabify all of Tacitus, Histores" in {
    val f = "jvm/src/test/resources/tacitus-histories-words.txt"
    val words = Source.fromFile(f).getLines.toVector.mkString(" ").split(" ").toVector
    for (w <- words) {
      //println(w + " => " + Latin23Alphabet.syllabify(w).mkString("-"))
    }
  }

}
