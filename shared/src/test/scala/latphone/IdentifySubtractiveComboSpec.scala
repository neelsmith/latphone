package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class IdentifySubtractiveComboSpec extends FlatSpec {

  "The LatinNumerics object" should "recognize subtractives values for 40" in {
    assert(LatinNumerics.valid(LatinNumerics.forty))
  }

}
