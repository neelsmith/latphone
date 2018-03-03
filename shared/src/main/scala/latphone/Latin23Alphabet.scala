package edu.holycross.shot.latin


object Latin23Alphabet extends LatinAlphabet {

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
    Set("a","e","i","o","u")
  }
  def semivowels: Set[String] = {
    Set("i","u")
  }

  def syllabify(ls: LatinString): Vector[String] = {
    Vector.empty
  }


  override def toString: String = {
    "Latin alphabet with 23 alphabetic characters."
  }

}
