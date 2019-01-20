package edu.holycross.shot.latin
import org.scalatest.FlatSpec

import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._


class LatinTextReaderDepunctSpec extends FlatSpec {




  "A LatinTextReader"  should "recognize a leading double quote as puncutation" in {
      val txt = """increpat "o patrum suboles oblita priorum,"""
      val urn = CtsUrn("urn:cts:latinLit:phi0881.phi003.latinlib:126")
      val cn = CitableNode(urn, txt)
      val tokens = LatinTextReader.nodeToTokens(cn, Latin24Alphabet)
      println(tokens)
  }
}
