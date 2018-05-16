package edu.holycross.shot.latin

import edu.holycross.shot.ohco2._

/**
*/
object LatinTextReader {



  /** Valid strings for abbreviated praenomina.*/
  val praenomina = Vector("Agr.", "Ap.", "A.", "K.", "D.", "F.", "C.", "Cn.", "L.", "Mam.", "M.", "M'.", "N.", "Oct.", "Opet.", "Post.", "Pro.", "P.", "Q.", "Sert.", "Ser.", "Sex.", "Sp.", "St.", "Ti", "T.", "V.", "Vol.", "Vop.")

  /** Determine [[LatinLexicalCategory]] for a string representing
  * a single token.
  * @param s String to analyze.
  * @param alphabet Alphabet the string is written in.
   */
  def lexicalCategory(s: String, alphabet: LatinAlphabet): LatinLexicalCategory = {
    if (alphabet.numerics.contains(s(0))) {
      if (alphabet.numeric(s)) {
        NumericToken
      } else {
        InvalidToken
      }
    } else if (alphabet.alphabetString.contains(s(0))) {
      if (alphabet.alphabetic(s)) {
        LexicalToken
      } else {
        InvalidToken
      }

    } else {
      InvalidToken
    }
  }


  /** Recursively convert a single white-space delimited block
  * to a Vector of one or more tokens by stripping
  * out any trailing punctuation tokens.
  *
  * @param s String to depunctuate.
  * @param alphabet Alphabet the string is written in.
  * @param depunctVector Vector of previously accumulated
  * strings.
  */
  def depunctuate (s: String, alphabet: LatinAlphabet, depunctVector: Vector[String] = Vector.empty): Vector[String] = {
    val trailChar = s"${s.last}"
    if (alphabet.punctuationString.contains(trailChar)) {
      val dropLast = s.reverse.tail.reverse
      depunctuate(dropLast, alphabet, trailChar +: depunctVector)
    } else {
      s +: depunctVector
    }
  }


  /** Tokenize a corpus.
  *
  * @param corpus Corpus to tokenize.
  * @param alphabet Latin alphabet used in corpus.
  */
  /*
  def fromCorpus(corpus: Corpus, alphabet:  LatinAlphabet): Vector[Option[LatinToken]] = {
    val tokens = for (n <- corpus.nodes) yield {
      nodeToToken(n)
    }
    tokens.flatten
  }*/
}
