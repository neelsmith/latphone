package edu.holycross.shot.latin


/** Behavior of a Latin alphabet.
*/
trait LatinAlphabet  {

  /** Ordered list of all allowed alphabetic characters. */
  def alphabetString: String

  /** List of all allowed punctuation characters. */
  def punctuationString: String

  /** Set of allowed diphthongs.*/
  def diphthongs: Set[String]

  /** Set of consonants.*/
  def consonants: Set[String]

  /** Set of vowels.*/
  def vowels: Set[String]

  /** Possiby empty set of semivowels.*/
  def semivowels: Set[String]


  /** True if a given non-whitespace character is defined
  * in this alphabet.
  *
  * @param c Character to check.
  */
  def  valid(c: Char): Boolean = {
    (alphabetString.contains(c) || punctuationString.contains(c))
  }

  /** True if a given string is valid in this alphabet.
  *
  * @param s String to check.
  */
  def valid(s: String): Boolean  = {
    val squeezeWhiteSpace = s.replaceAll("\\s", "")
    squeezeWhiteSpace.map(valid(_)).filter(_ == true).size == squeezeWhiteSpace.size
  }


  /** Divide a give [[LatinString]] into a Vector of
  * String values.
  *
  * @param ls [[LatinString]] to syllabify.
  */
  def syllabify(ls: LatinString): Vector[String]

}
