package edu.holycross.shot.latin
import scala.scalajs.js
import scala.scalajs.js.annotation._
/** Latin alphabet with 25 alphabetic characters.
*  Vocalic "i" and "u" are distinguished from consontal "j" and "v".
*/
@JSExportAll  object Latin25Alphabet extends LatinAlphabet {

  def label = """Latin alphabet with 25 alphabetic characters.
  Vocalic "i" and "u" are distinguished from consontal "j" and "v"."""
  //Regular expressions for syllabification
  /** RE for vowel-consonant-vowel pattern.*/
  val vcv = "(.*[aeiou])([bcdfghklmnpqrstvx®])([aeiou].*)".r
  /** RE for vowel+consonant cluster pattern.*/
  val consCluster = "(.*[aeiou])([bcdfghklmnpqrstvx]+)([bcdfghklmnpqrstvx®])([aeiou].*)".r
  /** RE for vowel+mute+liquid pattern.*/
  val muteLiquid = "(.*[aeiou])([bdgptc])([lr])([aeiou].*)".r
  /** RE for diphthong+vowel pattern.*/
  val diphVowel = "(.*)(ae|au|ei|eu|oe)([aeiou].*)".r
  /** RE for a followed by non-diphthong. */
  val aSplits = "(.*a)([io].*)".r
  /** RE for e followed by non-diphthong. */
  val eSplits = "(.*e)([ao].*)".r
  /** RE for i followed by non-diphthong. */
  val iSplits = "(.*i)([aeiou].*)".r
  /** RE for o followed by non-diphthong. */
  val oSplits = "(.*o)([aiou].*)".r
  /** RE for a followed by vowel. */
  val uSplits = "(.*u)([aeiou].*)".r

  // adjust semivowels
  /** RE for word-initial i followed by vowel. */
  val initialJ = "^i-([aeiou].+)".r
  /** RE for syllable-initial i followed by vowel. */
  val syllInitialJ = "(.+[aeiou])-([bcfghklmnpqrvx]?)i-([aeiou].+)".r
  /** RE for diphthong ei when it should be read as e+semivowel i. */
  val fakeDiphthongI = "(.*)ei-([aeiou].+)".r

  /** RE for word-initial i followed by vowel. */
  val initialV = "^u-([aeiou].+)".r
  /** RE for syllable-initial i followed by vowel. */
  val syllInitialV = "(.+[aeiou])-([bcfghklmnpqrvx]?)u-([aeiou].+)".r
  /** RE for diphthong ei when it should be read as e+semivowel u. */
  val fakeDiphthongU = "(.*)(au|eu)-([aeiou].+)".r

  /** Ordered sequence of alphabetic characters.*/
  def alphabetString: String = {
    "abcdefghiklmnopqrstuxyz"

  }
  /** Ordered sequence of allowed punctuation characters.*/
  def punctuationString: String = {
    "(),;:.?"
  }

  /** Set of all recognized diphthongs.*/
  def diphthongs: Set[String] = {
    Set("ae","au",
        "ei", "eu",
        "oe"
        //huius,cuius,huic,cui,hui are exceptions
    )
  }
  /** Set of all recognized consonants.*/
  def consonants: Set[String] = {
    Set("b","c","d","f","g","h","k","l","m","n","p","q","r","s","t","x","y","z")
  }
  /** Set of all recognized vowels.*/
  def vowels: Set[String] = {
    Set("a","e","o")
  }
  /** Set of all recognized semivowels.*/
  def semivowels: Set[String] = {
    Set("i","u")
  }


  /** Divide a given String into a Vector of
  * Strings.
  *
  * @param s String to syllabify.
  */
  def syllabify(s: String): Vector[String] = {
    val protectQu = s.replaceAll("qu","®").toLowerCase
    val a =   protectQu match {
      case aSplits(opening,apart) =>
      Vector(opening,apart).mkString("-")
      case _ => protectQu
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



    if (rule4.toLowerCase == protectQu) {
      val adjusted = restoreSemiConsonants(protectQu)
      adjusted.replaceAll("®","qu").split("-").toVector
    } else {
      syllabify(rule4)
    }
  }

  def restoreSemiConsonants(s: String) : String = {
    val i1 = s match {
      case initialJ(x) => "i" + x
      case _ => s
    }
    val i2 = i1  match {
      case syllInitialJ(start,cons,rest) =>  start + cons + "-i" + rest
      case _ => i1
    }
    val i3 = i2 match {
      case fakeDiphthongI(start,rest) => start + "e-i"  + rest
      case _ => i2
    }

    val   v1 = i3 match {
      case syllInitialV(start,cons,rest) =>  start + cons + "-u" + rest
      case _ => i3
    }

    val v2 = v1 match {
      case initialV(x) => "u" + x
      case _ => v1
    }

    val v3 = v2 match {
      case fakeDiphthongU(start,diph,rest) => {
        start + diph(0)  + "-" + diph(1) + rest
      }
      case _ => v2
    }
    v3
/*
    if (v3 == s) {
      s
    } else {
      syllabify(v3).mkString("-")
    }
    */
  }
  override def toString: String = {
    "Latin alphabet with 23 alphabetic characters."
  }

}
