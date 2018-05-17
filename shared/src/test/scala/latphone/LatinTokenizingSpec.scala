package edu.holycross.shot.latin
import org.scalatest.FlatSpec

import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._


class LatinTokenizingSpec extends FlatSpec {

  "A LatinTextReader"  should "accept leading and repeated whitespace when tokenizing" in {
    val u = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:3.42.6")
    val txt = " Romam tanti erant terrores allati ,"//" ut posito iam decemvirali odio patres vigilias in urbe habendas censerent , omnes , qui per aetatem arma ferre possent, custodire moenia ac pro portis stationes agere iuberent"

    val tokens = LatinTextReader.nodeToTokens(CitableNode(u, txt), Latin24Alphabet)
    assert(tokens.size == 6)

  }

}
