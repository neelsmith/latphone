package edu.holycross.shot.latin


/** A string of text in a specified [[LatinAlphabet]].
*
* @param s Text.
* @param alpha Alphabet the text belongs to.
*/
case class LatinString(s: String, alpha: LatinAlphabet)  {
  require(alpha.valid(s), s"${s} is not a valid Latin string the ${alpha}")

  override def toString: String = s
}
