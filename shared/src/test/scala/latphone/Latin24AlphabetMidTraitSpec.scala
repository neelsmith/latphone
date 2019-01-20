package edu.holycross.shot.latin
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import  edu.holycross.shot.mid.validator._

import org.scalatest.FlatSpec



class Latin24AlphabetMidTraitSpec extends FlatSpec {








  "The Latin24Alphabet object" should "recognize single quotes as punctuation" in {
    val cex = """urn:cts:latinLit:phi0881.phi003.latinlib:126#increpat "o patrum suboles oblita priorum,"""
    val corpus = Corpus(cex)
    val tokens = Latin24Alphabet.tokenizeCorpus(corpus)
    println(tokens)

  }
}
