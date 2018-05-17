package edu.holycross.shot.latin
import scala.scalajs.js
import scala.scalajs.js.annotation._

import edu.holycross.shot.cite._

/**
*/
@JSExportAll  case class LatinToken(urn: CtsUrn, text: String, category: LatinLexicalCategory ) {

  /** String serialization in delimited-text format.
  *
  * @param delimiter String to use as column delimiter.
  */
  def delimitedText(delimiter: String = "#"): String = {
    s"${urn}${delimiter}${text}${delimiter}${category}"
  }

  /** String serialization in CEX delimited-text format.
  */
  def cex : String = delimitedText("#")
}
