package edu.holycross.shot.latin

import edu.holycross.shot.mid.orthography._

import scala.scalajs.js
import scala.scalajs.js.annotation._

  // Extend MidTokenCategory with some useful categories:

/** Invalid token for a given alphabet.*/
@JSExportTopLevel("InvalidToken") case object InvalidToken extends MidTokenCategory  {def name = "invalid"}

/** Token outside this alphabet, possibly foreign term.*/
@JSExportTopLevel("ForeignToken")  case object ForeignToken extends MidTokenCategory  {def name = "foreign"}

/** .*/
@JSExportTopLevel("SyllableToken")  case object SyllableToken extends MidTokenCategory  {def name = "syllable"}
