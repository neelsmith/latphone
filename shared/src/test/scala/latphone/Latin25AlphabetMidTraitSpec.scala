package edu.holycross.shot.latin
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import edu.holycross.shot.mid.orthography._


import org.scalatest.FlatSpec



class Latin25AlphabetMidTraitSpec extends FlatSpec {








  "The Latin25Alphabet object" should "recognize single quotes as punctuation" in {
    val cex = """urn:cts:latinLit:phi0881.phi003.latinlib:126#increpat "o patrum suboles oblita priorum,"""
    val corpus = Corpus(cex)
    val tokens = Latin25Alphabet.tokenizeCorpus(corpus)
    println(tokens)

  }

  it should "recognize hyphen as enclitic delimiter" in {


    val cex = "urn:cts:omar:stoa0179.stoa001.omar:1.pr.7#suum conditoris-que sui parentem"
    val corpus = Corpus(cex)
    val tokens = Latin25Alphabet.tokenizeCorpus(corpus)
    println(tokens)
  }
}
