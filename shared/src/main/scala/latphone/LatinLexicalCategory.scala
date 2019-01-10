package edu.holycross.shot.latin
import edu.holycross.shot.mid.validator._

import scala.scalajs.js
import scala.scalajs.js.annotation._

/** All possible lexical categories for a token
* are enumerated by case objects extending this trait
*
* The `name` member must be implemented with an English description of the lexical category.
*/
@JSExportAll  sealed trait LatinLexicalCategory {def name : String}

/** Parseable lexical token. */
case object LexicalToken extends LatinLexicalCategory with MidTokenCategory {val name = "lexical token"}

/** Parseable numeric token. */
case object NumericToken extends LatinLexicalCategory with MidTokenCategory  {val name = "numeric token"}

/** Single punctuation character. */
case object PunctuationToken extends LatinLexicalCategory with MidTokenCategory {val name = "punctuation"}

/** Abbreviated praenomen. */
case object PraenomenToken extends LatinLexicalCategory with MidTokenCategory {val name = "praenomen"}

/** Invalid token for a given alphabet.*/
case object InvalidToken extends LatinLexicalCategory with MidTokenCategory {val name = "invalid"}
