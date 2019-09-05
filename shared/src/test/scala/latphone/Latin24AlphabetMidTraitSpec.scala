package edu.holycross.shot.latin
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import  edu.holycross.shot.mid.validator._

import org.scalatest.FlatSpec



class Latin24AlphabetMidTraitSpec extends FlatSpec {








  "The Latin24Alphabet object" should "recognize double quotes as punctuation" in {
    val cex = """urn:cts:latinLit:phi0881.phi003.latinlib:126#increpat "o patrum suboles oblita priorum,"""
    val corpus = Corpus(cex)
    val tokens = Latin24Alphabet.tokenizeCorpus(corpus)
    println(tokens)

  }

  it should "recognize plus sign as enclitic delimiter" in {


    val cex = "urn:cts:omar:stoa0179.stoa001.omar:1.pr.7#suum conditoris+que sui parentem"
    val corpus = Corpus(cex)
    val tokens = Latin24Alphabet.tokenizeCorpus(corpus)
    println(tokens)
  }

  it should "recognize single quote as elision marker" in pending /*{

  }*/
}
