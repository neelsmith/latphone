package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinSubtractiveComboSpec extends FlatSpec {

  "The LatinNumerics object" should "recognize subtractives values for 40" in {
    assert(LatinNumerics.valid(LatinNumerics.forty))
  }

  it should "reject additive values for 40" in {
    val badForty = s"${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}"
    //assert(LatinNumerics.valid(badForty) == false)
    //assert(LatinNumerics.numericToInt(badForty) == 40)
  }

}
