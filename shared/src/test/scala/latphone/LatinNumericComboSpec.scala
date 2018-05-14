package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinNumericComboSpec extends FlatSpec {

  val fourteen = s"${LatinNumerics.ten}${LatinNumerics.four}"

  "The LatinNumerics object" should "accept duplicated ten values for 20, 30, 70 and 80" in {
    //assert(LatinNumerics.valid(s"${LatinNumerics.ten}"))
    //assert(LatinNumerics.valid(s"${LatinNumerics.ten}${LatinNumerics.ten}"))
    assert(LatinNumerics.valid(s"${LatinNumerics.fifty}${LatinNumerics.ten}${LatinNumerics.ten}"))
  }
  it should "reject additive values for 40" in {
    //assert(LatinNumerics.valid(s"${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}") == false)
  }

}
