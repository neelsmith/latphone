package edu.holycross.shot.latin
import edu.holycross.shot.mid.validator._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import scala.scalajs.js
import scala.scalajs.js.annotation._

/** Latin alphabet with 23 alphabetic characters.
* "i" and "u" are treated as semivowels.
*/
@JSExportAll  object Latin23Syllable extends MidOrthography {


  /** Descriptive phrase required by MidOrthography trait.*/
  def orthography = "Latin alphabet with 23 alphabetic characters tokenizing by syllable"

  // required for MidOrthography
  def validCP(cp: Int): Boolean  = {
    Latin23Alphabet.validCP(cp)
  }

  // required for MidOrthography
  def tokenizeNode(n: CitableNode): Vector[MidToken] = {
    val newVersion = n.urn.addVersion(n.urn.versionOption.getOrElse("") + "_sylls")

    val tokens = n.text.split("\\s").filter(_.nonEmpty)
    val sylls = for (t <- tokens) yield {
      Latin23Alphabet.syllabify(t)
    }

    val syllableTokens = for (syll <- sylls.flatten.zipWithIndex) yield {
      val trimmed = syll._1.trim
      val newPassage = n.urn.passageComponent + "." + syll._2
      val newUrn = CtsUrn(newVersion.dropPassage.toString + newPassage)

      MidToken(newUrn, trimmed, Some(SyllableToken))
    }
    syllableTokens.toVector
  }

  // required for MidOrthography
  def tokenCategories: Vector[MidTokenCategory]  = {
    Vector(SyllableToken, PunctuationToken)
  }

}
