package edu.holycross.shot.latin

import edu.holycross.shot.mid.orthography._
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import scala.scalajs.js
import scala.scalajs.js.annotation._

/** Latin alphabet with 23 alphabetic characters.
* "i" and "u" are treated as semivowels.
*/
@JSExportAll  object Latin23Syllable extends MidOrthography {


  //////////////////////////////////////////////////
  //
  // 5 methods required by MidOrthography trait
  //
  /** 1. Descriptive phrase required by MidOrthography trait.*/
  def orthography = "Latin alphabet with 23 alphabetic characters tokenizing by syllable"

  // 2. required for MidOrthography
  def validCP(cp: Int): Boolean  = {
    Latin23Alphabet.validCP(cp)
  }

  // 3. Required for MidOrthography
  def tokenCategories: Vector[MidTokenCategory]  = {
    Vector(SyllableToken, PunctuationToken)
  }

  // 4. Required for MidOrthography.
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
  /** 5. Value for exemplar ID in tokenzied editions. */
  def exemplarId: String = "lat23syll"



}
