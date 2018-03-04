package edu.holycross.shot.latin

/** Latin alphabet with 23 alphabetic characters.
* "i" and "u" are treated as semivowels.
*/
object Latin23Alphabet extends LatinAlphabet {
  val vcv = "(.*[aeiou])([bcdfghklmnpqrstvx])([aeiou].*)".r



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

  def syllabify(s: String): Vector[String] = {
    val rule1 = s match {
      case vcv(open,cons,close) =>
      syllabify(
        Vector(open,cons + close).mkString("-")).mkString("-")
      case _ => s
    }
    /*

    Rules for syllabication:


    1. Single conson between 2 vowels goes with following syll.  do-mus a-mi-cus ful-men
    2.  when 2+ consons separate vowels, the last goes w following syllab.  sanc-tus dig-punctuationString
        -  But mute (bdgptc)+ liquid (lr) is an exception.  pu-bli-cus ce-le-bro

    3.  Etymology rules.



    */

    rule1.split("-").toVector
    //Vector.empty
  }


  override def toString: String = {
    "Latin alphabet with 23 alphabetic characters."
  }

}
