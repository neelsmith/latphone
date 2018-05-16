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

  /** List of all allowed numeric characters. */
  def numerics: Vector[Char] = Vector(
    LatinNumerics.one,
    LatinNumerics.two,
    LatinNumerics.three,
    LatinNumerics.four,
    LatinNumerics.five,
    LatinNumerics.six,
    LatinNumerics.seven,
    LatinNumerics.eight,
    LatinNumerics.nine,
    LatinNumerics.ten,
    LatinNumerics.fifty,
    LatinNumerics.hundred,
    LatinNumerics.fiveHundred,
    LatinNumerics.thousand,
    LatinNumerics.fiveThousand,
    LatinNumerics.tenThousand
  )

  /** True if s is composed only of numeric characters.
  *
  * @param s String to check.
  */
  def numeric(s: String) : Boolean = {
    val numericsOnly = s.filter(c => numerics.contains(c.toUpper))
    (numericsOnly.size == s.size)
  }

  /** True if s is composed only of alphabetic characters.
  *
  * @param s String to check.
  */
  def alphabetic(s: String) : Boolean = {
    val alphaOnly = s.filter(c => alphabetString.contains(c.toUpper))
    (alphaOnly.size == s.size)
  }



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
    (alphabetString.contains(c.toLower) || punctuationString.contains(c.toLower) || numerics.contains(c.toUpper) )
  }

  /** True if a given string is valid in this alphabet.
  *
  * @param s String to check.
  */
  def valid(s: String): Boolean  = {
    val squeezeWhiteSpace = s.replaceAll("\\s", "")
    squeezeWhiteSpace.map(valid(_)).filter(_ == true).size == squeezeWhiteSpace.size
  }


  /** Divide a given String into a Vector of
  * Strings.
  *
  * @param s String to syllabify.
  */
  def syllabify(s: String): Vector[String]

}
