package edu.holycross.shot.latin


/** Behavior of a case-insensitive Latin alphabet.  All alphabetic and punctuation characters
* must be explicitly enumerated in lower-case form.
* White space is ignored in
* evaluating whether a String is valid in a given alphabet.
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
  /** True if s is a consonant.
  *
  * @param s String to check.
  */
  def consonant(s: String) : Boolean = {
    consonants.contains(s.map(_.toLower))
  }

  /** Possiby empty set of semivowels.*/
  def semivowels: Set[String]
  /** True if s is a semivowel.
  *
  * @param s String to check.
  */
  def semivowel(s: String) : Boolean = {
    semivowels.contains(s.map(_.toLower))
  }

  /** Set of vowels.*/
  def vowels: Set[String]
  /** True if s is a vowel.
  *
  * @param s String to check.
  */
  def vowel(s: String) : Boolean = {
    vowels.contains(s.map(_.toLower))
  }

  /** True if a given non-whitespace character is defined
  * in this alphabet.
  *
  * @param c Character to check.
  */
  def  valid(c: Char): Boolean = {
    (alphabetString.contains(c.toLower) || punctuationString.contains(c.toLower))
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
  def syllabify(s: String): Vector[String]

}
