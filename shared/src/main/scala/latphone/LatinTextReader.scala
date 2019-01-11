package edu.holycross.shot.latin
import scala.scalajs.js
import scala.scalajs.js.annotation._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.mid.validator._

/**  An object for reading and tokenizing plain-text documents  in Latin.
*/
@JSExportAll object LatinTextReader {

  /** Valid strings for abbreviated praenomina in plain-text
  * editions.
  */
  val praenomina = Vector("Agr.", "Ap.", "A.", "K.", "D.", "F.", "C.", "Cn.", "L.", "Mam.", "M.", "M'.", "N.", "Oct.", "Opet.", "Post.", "Pro.", "P.", "Q.", "Sert.", "Ser.", "Sex.", "Sp.", "St.", "Ti", "T.", "V.", "Vol.", "Vop.")

  /** Determine [[LatinLexicalCategory]] for a string representing
  * a single token.
  * @param s String to analyze.
  * @param alphabet Alphabet the string is written in.
   */
  def lexicalCategory(s: String, alphabet: LatinAlphabet): Option[MidTokenCategory] = {
    if (alphabet.numerics.contains(s(0).toUpper)) {
      if (alphabet.numeric(s)) {
        Some(NumericToken)
      } else {
        None
      }
    } else if (alphabet.alphabetString.contains(s(0).toLower)) {
      if (alphabet.alphabetic(s)) {
        Some(LexicalToken)
      } else {
        None
      }

    } else {
      None
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
      if (dropLast.nonEmpty) {
        depunctuate(dropLast, alphabet, trailChar +: depunctVector)
      } else {
        s +: depunctVector
      }

    } else {
      s +: depunctVector
    }
  }



  /** Generate a Vector of tokens for a given citable node.
  *
  * @param n `CitableNode` to analyze.
  * @param alphabet Alphabet the node is written in.
  */
  def nodeToTokens(n: CitableNode, alphabet: LatinAlphabet) : Vector[MidToken] = {
    val urn = n.urn
    val units = n.text.split(" ").filter(_.nonEmpty)

    val classified = for (unit <- units.zipWithIndex) yield {
      val newPassage = urn.passageComponent + "." + unit._2
      val newUrn = CtsUrn(urn.dropPassage.toString + newPassage)

      //println(unit + " in " + newUrn)
      // process praenomina first since "." is part
      // of the token:
      val tokenClass: Vector[MidToken] = if (praenomina.contains(unit._1)) {
        Vector(MidToken(newUrn, unit._1, Some(PraenomenToken)))

      } else {
        val depunctuated = depunctuate(unit._1, alphabet)
        val first =  MidToken(newUrn, depunctuated.head, lexicalCategory(depunctuated.head, alphabet))

        val trailingPunct = for (punct <- depunctuated.tail zipWithIndex) yield {
          MidToken(CtsUrn(newUrn + "_" + punct._2), punct._1, Some(PunctuationToken))
        }
        first +: trailingPunct

      }
      //println("Token class is " + tokenClass)
      tokenClass
    }
    //println("Classified is " + classified.toVector.flatten)
    classified.toVector.flatten
  }

  /** Generate a Vector of tokens for a given corpus.
  * @param n `CitableNode` to analyze.
  * @param alphabet Alphabet the corpus is written in.
  */
  def corpusToTokens(corpus: Corpus, alphabet: LatinAlphabet): Vector[MidToken] = {
    def tokens = for (n <- corpus.nodes zipWithIndex) yield {
      LatinTextReader.nodeToTokens(n._1, alphabet)
    }
    tokens.flatten
  }

}
