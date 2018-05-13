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
    '\u2160',
    '\u2161',
    '\u2162',
    '\u2163',
    '\u2164',
    '\u2165',
    '\u2166',
    '\u2167',
    '\u2168',
    '\u2169',

    '\u216C',
    '\u216D',
    '\u216E',
    '\u216F',

    '\u2181',
    '\u2182'
  )
  /** True if s is composed only of numeric characters.
  *
  * @param s String to check.
  */
  def numeric(s: String) : Boolean = {
    for (c <- s) {
      require(numerics.contains(c.toUpper), s"${c} is not a numeric character.")
    }
    true
  }

  /** Convert numeric character to corresponding integer value.
  * It is an exception if c is not a numeric character.
  *
  * @param c Numeric character to convert.
  */
  def numericToInt(c: Char) : Int = {
     c match {
      case '\u2160' => 1
      case '\u2161' => 2
      case '\u2162' => 3
      case '\u2163' => 4
      case '\u2164' => 5
      case '\u2165' => 6
      case '\u2166' => 7
      case '\u2167' => 8
      case '\u2168' => 9
      case '\u2169' => 10
      case '\u216C' => 50
      case '\u216D' => 100
      case '\u216E' => 500
      case '\u216F' => 1000
      case '\u2181' => 5000
      case '\u2182' => 10000
      case _ => throw new Exception(s"LatinAlphabet: ${c} is not a valid numeric character.")
    }
  }


  /** Sum up the integer values of a string of numeric characters..
  * It is an exception if any character of s is not a numeric character.
  *
  * @param s String of numeric characters to sum up.
  */
  def numericToInt(s: String, total : Int = 0) : Int = {
    val forty = "\u2169\u216C"
    val ninety = "\u2169\u216D"

    if (s.size == 0) {
      total
    } else if (s.contains(forty)) {
      numericToInt(s.replaceFirst(forty,""), total + 40)
    } else if (s.contains(ninety)){
      numericToInt(s.replaceFirst(ninety,""), total + 90)
    } else if (s.size == 1) {
      total + numericToInt(s.head)

    } else {
      val subTotal = total + numericToInt(s.head)
      numericToInt(s.tail, subTotal)
    }

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
