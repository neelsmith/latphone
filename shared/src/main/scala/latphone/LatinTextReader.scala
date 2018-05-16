package edu.holycross.shot.latin

import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._

/**
*/
object LatinTextReader {

  /** Valid strings for abbreviated praenomina in plain-text
  * editions.
  */
  val praenomina = Vector("Agr.", "Ap.", "A.", "K.", "D.", "F.", "C.", "Cn.", "L.", "Mam.", "M.", "M'.", "N.", "Oct.", "Opet.", "Post.", "Pro.", "P.", "Q.", "Sert.", "Ser.", "Sex.", "Sp.", "St.", "Ti", "T.", "V.", "Vol.", "Vop.")

  /** Determine [[LatinLexicalCategory]] for a string representing
  * a single token.
  * @param s String to analyze.
  * @param alphabet Alphabet the string is written in.
   */
  def lexicalCategory(s: String, alphabet: LatinAlphabet): LatinLexicalCategory = {
    if (alphabet.numerics.contains(s(0).toUpper)) {
      if (alphabet.numeric(s)) {
        NumericToken
      } else {
        InvalidToken
      }
    } else if (alphabet.alphabetString.contains(s(0).toLower)) {
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



  /** Generate a Vector of tokens for a given citable node.
  *
  * @param n `CitableNode` to analyze.
  * @param alphabet Alphabet the nodes is written in.
  */
  def nodeToTokens(n: CitableNode, alphabet: LatinAlphabet) = {//b: Vector[LatinToken] = {
    val urn = n.urn
    val units = n.text.split(" ")
    val classified = for (unit <- units.zipWithIndex) yield {
      val newPassage = urn.passageComponent + "." + unit._2
      val newUrn = CtsUrn(urn.dropPassage.toString + newPassage)

      //println(unit + " in " + newUrn)
      // process praenomina first since "." is part
      // of the token:
      val tokenClass: Vector[LatinToken] = if (praenomina.contains(unit._1)) {
        Vector(LatinToken(newUrn, unit._1, Praenomen))

      } else {
        val depunctuated = depunctuate(unit._1, alphabet)
        val first =  LatinToken(newUrn, depunctuated.head, lexicalCategory(depunctuated.head, alphabet))

        val trailingPunct = for (punct <- depunctuated.tail zipWithIndex) yield {
          LatinToken(CtsUrn(newUrn + "_" + punct._2), punct._1, Punctuation)
        }
        first +: trailingPunct
        /*
        val depunctuated = unit._1.split(alphabet.punctuationString.toArray)
        if (depunctuated.size > 1) {
          depunctuate(depunctuated, newUrn, alphabet)
          Vector.empty[LatinToken]
        } else {
          val cat = lexicalCategory(unit._1, alphabet)
          Vector(LatinToken(newUrn, unit._1,cat))
        }*/
      }
      //println("Token class is " + tokenClass)
      tokenClass
    }
    //println("Classified is " + classified.toVector.flatten)
    classified.toVector.flatten
  }

}
