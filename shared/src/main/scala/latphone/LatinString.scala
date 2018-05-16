package edu.holycross.shot.latin
import scala.scalajs.js
import scala.scalajs.js.annotation._

/** A string of text in a specified [[LatinAlphabet]].
*
* @param s Text.
* @param alpha Alphabet the text belongs to.
*/
@JSExportAll  case class LatinString(s: String, alpha: LatinAlphabet)  {
  require(alpha.valid(s), s"${s} is not a valid Latin string the ${alpha}")

  def syllabify = alpha.syllabify(s)
  override def toString: String = s

  def size: Int = s.size


  /** Convert a numeric token to an integer value.
  * It is an error if [[s]] is not a numeric token
  */
  def toInt: Int = {
    0
  }

}
