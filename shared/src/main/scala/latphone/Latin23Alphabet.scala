package edu.holycross.shot.latin

/** Latin alphabet with 23 alphabetic characters.
* "i" and "u" are treated as semivowels.
*/
object Latin23Alphabet extends LatinAlphabet {

  //Regular expressions for syllabification
  /** RE for vowel-consonant-vowel pattern.*/
  val vcv = "(.*[aeiou])([bcdfghklmnpqrstvx])([aeiou].*)".r
  /** RE for vowel+consonant cluster pattern.*/
  val consCluster = "(.*[aeiou])([bcdfghklmnpqrstvx]+)([bcdfghklmnpqrstvx])([aeiou].*)".r
  /** RE for vowel+mute+liquid pattern.*/
  val muteLiquid = "(.*[aeiou])([bdgptc])([lr])([aeiou].*)".r
  /** RE for diphthong+vowel pattern.*/
  val diphVowel = "(.*)(ae|au|ei|eu|oe)([aeiou].*)".r

  val aSplits = "(.*a)([io].*)".r
  val eSplits = "(.*e)([ao].*)".r
  val iSplits = "(.*i)([aeiou].*)".r
  val oSplits = "(.*o)([aiou].*)".r
  val uSplits = "(.*u)([aeiou].*)".r

  def alphabetString: String = {
    "abcdefghiklmnopqrstuxyz"

  }
  def punctuationString: String = {
    ",.:;?()"
  }

  def diphthongs: Set[String] = {
    Set("ae","au",
        "ei", "eu",
        "oe"
        //huius,cuius,huic,cui,hui are exceptions
    )
  }
  def consonants: Set[String] = {
    Set("b","c","d","f","g","h","k","l","m","n","p","q","r","s","t","x","y","z")
  }
  def vowels: Set[String] = {
    Set("a","e","o")
  }
  def semivowels: Set[String] = {
    Set("i","u")
  }


  /** Divide a given String into a Vector of
  * Strings.
  *
  * @param s String to syllabify.
  */
  def syllabify(s: String): Vector[String] = {

    val a =   s match {
      case aSplits(opening,apart) =>
      Vector(opening,apart).mkString("-")
      case _ => s
    }
    val e =  a match {
      case eSplits(opening,epart) =>
      Vector(opening,epart).mkString("-")
      case _ => a
    }
    val i =  e match {
      case iSplits(opening,ipart) =>
      Vector(opening,ipart).mkString("-")
      case _ => e
    }
    val o =  i match {
      case oSplits(opening,opart) =>
      Vector(opening,opart).mkString("-")
      case _ => i
    }
    val u =  o match {
      case uSplits(opening,upart) =>
      Vector(opening,upart).mkString("-")
      case _ => o
    }
    // Rules for grouping consonants:
    val rule1 = u match {
      case vcv(open,cons,close) =>
        Vector(open,cons + close).mkString("-")
      case _ => u
    }
    val rule2 = rule1 match {
      case muteLiquid(open,mute,liquid,close) =>
       Vector(open, mute + liquid + close).mkString("-")
      case _ => rule1
    }
    val rule3 = rule2 match {
      case consCluster(open,cluster,lastC,close) =>
        Vector(open +cluster, lastC + close).mkString("-")
      case _ => rule2
    }
    val rule4 = rule3 match {
        case diphVowel(open,diphthong,close) =>
        Vector(open + diphthong, close).mkString("-")
      case _ => rule3
    }



    if (rule4 == s) {
      val adjusted = restoreSemiConsonants(rule4)
      adjusted.split("-").toVector
    } else {
      syllabify(rule4)
    }
  }

  def restoreSemiConsonants(s: String) : String = {
    s
  }
  override def toString: String = {
    "Latin alphabet with 23 alphabetic characters."
  }

}
