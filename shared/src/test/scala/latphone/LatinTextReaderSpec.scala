package edu.holycross.shot.latin
import org.scalatest.FlatSpec

import edu.holycross.shot.ohco2._


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
  /*
urn:cts:omar:stoa0179.stoa001.omar:1.4.2#seu ita rata, seu quia deus auctor cul\
pae honestior erat, Martem incertae stirpis patrem nuncupat.
urn:cts:omar:stoa0179.stoa001.omar:1.4.3#sed nec dii nec homines aut ipsam aut \
stirpem a crudelitate regia vindicant;
urn:cts:omar:stoa0179.stoa001.omar:1.4.4#sacerdos vincta in custodiam datur; pu\
eros in profluentem aquam mitti iubet. forte quadam divinitus super ripas Tiber\
is effusus lenibus stagnis nec adiri usquam ad iusti cursum poterat amnis et po\
sse quamvis languida mergi aqua infantes spem ferentibus dabat.
  urn:cts:omar:stoa0179.stoa001.omar:1.4.5#ita, velut defuncti regis imperio, in \
proxima eluvie, ubi nunc ficus Ruminalis est — Romularem vocatam ferunt—, puero\
s exponunt. vastae tum in his locis solitudines erant.
urn:cts:omar:stoa0179.stoa001.omar:1.4.6#tenet fama,  cum fluitantem alveum, qu\
o expositi erant pueri, tenuis in sicco aqua destituisset, lupam sitientem ex m\
ontibus, qui circa sunt, ad puerilem vagitum cursum flexisse; eam summissas inf\
antibus adeo mitem praebuisse mammas, ut lingua lambentem pueros magister regii\
 pecoris invenerit—
urn:cts:omar:stoa0179.stoa001.omar:1.4.7#Faustulo fuisse nomen ferunt—; ab eo a\
d stabula Larentiae uxori educandos datos. sunt, qui Larentiam vulgato corpore \
lupam inter pastores vocatam putent; inde locum fabulae ac miraculo datum.
urn:cts:omar:stoa0179.stoa001.omar:1.4.8#ita geniti itaque educati, cum primum \
adolevit aetas, nec in stabulis nec ad pecora segnes, venando peragrare saltus.
urn:cts:omar:stoa0179.stoa001.omar:1.4.9#hinc robore corporibus animisque sumpt\
o iam non feras tantum subsistere, sed in latrones praeda onustos impetus facer\
e pastoribusque rapta dividere et cum his crescente in dies grege iuvenum seria\
 ac iocos celebrare.*/




  "A LatinTextReader"  should "depunctuate a token block into individual tokens" in {
    val s = "corpus,"
    val actual = LatinTextReader.depunctuate(s, Latin24Alphabet)
    val expected = Vector("corpus", ",")
    assert(actual == expected)
  }

  it should "determine the lexical category of a single token" in {

    // pure alphabetic chars:
    assert (LatinTextReader.lexicalCategory("consul", Latin24Alphabet) == LexicalToken)

    // genuine numeric chars:
    val seventeen = "ⅩⅦ"
    assert (LatinTextReader.lexicalCategory(seventeen, Latin24Alphabet) == NumericToken)
  }

  it should "be case-insensitive" in {
    assert (LatinTextReader.lexicalCategory("Lucretius", Latin24Alphabet) == LexicalToken)
  }

  it should "reject tokens with mixed character types" in {
    // no good!  Alphabetic "X" with numeric seven!
    val mixedAlphaNum = "XⅧ	"
    assert (LatinTextReader.lexicalCategory(mixedAlphaNum, Latin24Alphabet) == InvalidToken)

    // numeric ten with alphabetic "VII"
    val mixedNumAlph = "ⅩVII"
    assert (LatinTextReader.lexicalCategory(mixedNumAlph, Latin24Alphabet) == InvalidToken)
  }

  it should "generate a vector of tokens from a citable node" in {
    val corpus = TextRepository(livyTiny).corpus
    val tks = LatinTextReader.nodeToTokens(corpus.nodes(0), Latin24Alphabet)
    assert(tks.size == 5)
    assert(tks.filter(_.category == LexicalToken).size == 3)
    assert(tks.filter(_.category == Punctuation).size == 1)
    assert(tks.filter(_.category == Praenomen).size == 1)
  }

    //val corpus = TextRepository(livyOneFourOne).corpus
    //val tokens = LatinTextReader.fromCorpus(corpus, Latin24Alphabet)

}