package edu.holycross.shot.latphone

/** All possible lexical categories for a token
* are enumerated by case objects extending this trait
*
* The `name` member must be implemented with an English description of the lexical category
*
* Used by [[edu.holycross.shot.mid.latinmodel.MIDToken]] and therefore also by [[edu.holycross.shot.mid.latinmodel.LatinTeiReader]]
*/
sealed trait LatinLexicalCategory {def name : String}

/** Parseable lexical token. */
case object LexicalToken extends LatinLexicalCategory {val name = "lexical token"}

/** Parseable numeric token. */
case object NumericToken extends LatinLexicalCategory {val name = "numeric token"}

/** Single punctuation character. */
case object Punctuation extends LatinLexicalCategory {val name = "punctuation"}

/** Abbreviated praenomen. */
case object Praenomen extends LatinLexicalCategory {val name = "praenomen"}
