package edu.holycross.shot.latin
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import edu.holycross.shot.mid.orthography._


import org.scalatest.FlatSpec



class Latin23AlphabetMidTraitSpec extends FlatSpec {



  val cex = """urn:cts:omar:stoa0179.stoa001.omar:1.4.4#sacerdos vincta in custodiam datur; pueros in profluentem aquam mitti iubet. forte quadam divinitus super ripas Tiberis effusus lenibus stagnis nec adiri usquam ad iusti cursum poterat amnis et posse quamvis languida mergi aqua infantes spem ferentibus dabat.
urn:cts:omar:stoa0179.stoa001.omar:1.4.5#ita, velut defuncti regis imperio, in proxima eluvie, ubi nunc ficus Ruminalis est — Romularem vocatam ferunt—, pueros exponunt. vastae tum in his locis solitudines erant.
urn:cts:omar:stoa0179.stoa001.omar:1.4.6#tenet fama, cum fluitantem alveum, quo expositi erant pueri, tenuis in sicco aqua destituisset, lupam sitientem ex montibus, qui circa sunt, ad puerilem vagitum cursum flexisse; eam summissas infantibus adeo mitem praebuisse mammas, ut lingua lambentem pueros magister regii pecoris invenerit—
urn:cts:omar:stoa0179.stoa001.omar:1.4.7#Faustulo fuisse nomen ferunt—; ab eo ad stabula Larentiae uxori educandos datos. sunt, qui Larentiam vulgato corpore lupam inter pastores vocatam putent; inde locum fabulae ac miraculo datum.
urn:cts:omar:stoa0179.stoa001.omar:1.4.8#ita geniti itaque educati, cum primum adolevit aetas, nec in stabulis nec ad pecora segnes, venando peragrare saltus.
urn:cts:omar:stoa0179.stoa001.omar:1.4.9#hinc robore corporibus animis+que sumpto iam non feras tantum subsistere, sed in latrones praeda onustos impetus facere pastoribus+que rapta dividere et cum his crescente in dies grege iuvenum seria ac iocos celebrare.
"""
  val corpus = Corpus(cex)




  "The Latin23Alphabet object" should "implement the MidOrthography's orthography function" in {
    assert(Latin23Alphabet.orthography == "Latin alphabet with 23 alphabetic characters")
  }
  it should "implement the MidOrthography's validCP function" in {
    assert(Latin23Alphabet.validCP('a'.toInt))
    assert(Latin23Alphabet.validCP('β'.toInt) == false)
  }



  it should "implement the MidOrthography's tokenCategories function" in {
    val expected =     Vector(PraenomenToken, PunctuationToken, LexicalToken, NumericToken, InvalidToken)
    assert(Latin23Alphabet.tokenCategories == expected)
  }

  it should "implement the MidOrthography's tokenizeNode function" in {
    val n = CitableNode(CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:2.8.4"), "creatus Sp. Lucretius consul,")
    val tokens = Latin23Alphabet.tokenizeNode(n)

    val expectedNumber = 5
    assert(tokens.size == expectedNumber)
    assert(tokens.filter(_.tokenCategory.get == LexicalToken).size == 3)
    assert(tokens.filter(_.tokenCategory.get == PunctuationToken).size == 1)
    assert(tokens.filter(_.tokenCategory.get == PraenomenToken).size == 1)
  }

  it should "inherit the concrete tokenizeCorpus function" in {

    val tokens: Vector[MidToken] = Latin23Alphabet.tokenizeCorpus(corpus)
    val expectedSize = 211
    //assert(tokens.size == expectedSize)
    println("ALL TOKENS:")
    println(tokens.map(_.string).distinct.sorted.mkString("\n"))
  }

  it should "correctly sort token histograms with the inherited tokenHistogram function" in {
    val tokens: Vector[MidToken] = Latin23Alphabet.tokenizeCorpus(corpus)
    //println(MidOrthography.tokenHistogram(tokens))
  }

  it should "recognize hyphen as a morpheme boundary" in {
    val ablabs = "urn:cts:omar:stoa0179.stoa001.omar:1.4.9#hinc robore corporibus animis+que sumpto"
    val tinyCorpus = Corpus(ablabs)
    val tokens: Vector[MidToken] = Latin23Alphabet.tokenizeCorpus(tinyCorpus)


    assert(tokens.size == 6)
  }



}
