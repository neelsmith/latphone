package edu.holycross.shot.latin
import org.scalatest.FlatSpec

import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._


class LatinTextReaderSpec extends FlatSpec {

val livyTiny = """#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:omar:stoa0179.stoa001.omar:#book,chapter,section#Livy#History of Rome#omar edition##true#lat
#!ctsdata
urn:cts:omar:stoa0179.stoa001.omar:2.8.4#creatus Sp. Lucretius consul,
"""
val livyTwoEightFour = """#!ctscatalog
urn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang
urn:cts:omar:stoa0179.stoa001.omar:#book,chapter,section#Livy#History of Rome#omar edition##true#lat
#!ctsdata
urn:cts:omar:stoa0179.stoa001.omar:2.8.4#creatus Sp. Lucretius consul, qui magno natu non sufficientibus iam viribus ad consularia munera obeunda intra paucos dies moritur. suffectus in Lucreti locum M. Horatius Pulvillus.
"""
  val livyOneFourOne = "#!ctscatalog\nurn#citationScheme#groupName#workTitle#versionLabel#exemplarLabel#online#lang\nurn:cts:omar:stoa0179.stoa001.omar:#book,chapter,section#Livy#History of Rome#omar edition##true#lat\n\n#!ctsdata\nurn:cts:omar:stoa0179.stoa001.omar:1.4.1#sed debebatur, ut opinor, fatis tantae origo urbis maximique secundum deorum opes imperii principium. vi compressa Vestalis cum geminum partum edidisset,"


  "A LatinTextReader"  should "depunctuate a token block into individual tokens" in {
    val s = "corpus,"
    val actual = LatinTextReader.depunctuate(s, Latin24Alphabet)
    val expected = Vector("corpus", ",")
    assert(actual == expected)
  }

  it should "determine the lexical category of a single token" in {

    // pure alphabetic chars:
    assert (LatinTextReader.lexicalCategory("consul", Latin23Alphabet).get == LexicalToken)

    // genuine numeric chars:
    val seventeen = "ⅩⅦ"
    assert (LatinTextReader.lexicalCategory(seventeen, Latin23Alphabet).get == NumericToken)
  }

  it should "be case-insensitive" in  {
    assert (LatinTextReader.lexicalCategory("Lucretius", Latin24Alphabet).get == LexicalToken)
  }

  it should "reject tokens with mixed character types" in {
    // no good!  Alphabetic "X" with numeric seven!
    val mixedAlphaNum = "XⅧ	"
    assert (LatinTextReader.lexicalCategory(mixedAlphaNum, Latin24Alphabet) == None)

    // numeric ten with alphabetic "VII"
    val mixedNumAlph = "ⅩVII"
    assert (LatinTextReader.lexicalCategory(mixedNumAlph, Latin24Alphabet) == None)
  }

  it should "generate a vector of tokens from a citable node" in  {
    val corpus = TextRepository(livyTiny).corpus
    val tks = LatinTextReader.nodeToTokens(corpus.nodes(0), Latin23Alphabet)
    assert(tks.size == 5)

    assert(tks.filter(_.tokenCategory.get == LexicalToken).size == 3)
    assert(tks.filter(_.tokenCategory.get == PunctuationToken).size == 1)
    assert(tks.filter(_.tokenCategory.get == PraenomenToken).size == 1)
  }

  it should "accept leading and repeated whitespace when tokenizing" in {
    val u = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:3.42.6")
    val txt = " Romam tanti erant terrores allati ,"
    val tokens = LatinTextReader.nodeToTokens(CitableNode(u, txt), Latin24Alphabet)
    assert(tokens.size == 6)
  }


  it should "generate a vector of tokens from a corpus" in {
    val c1 = TextRepository(livyOneFourOne).corpus
    val c2 = TextRepository(livyTwoEightFour).corpus

    val composite = Corpus.composite(Vector(c1,c2))
    val tokens = LatinTextReader.corpusToTokens(composite, Latin24Alphabet)
    val expectedTokens = 54
    assert(tokens.size == expectedTokens)
  }
}
