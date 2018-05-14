package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinNumeric2Spec extends FlatSpec {


  "The LatinNumerics object" should "accept an empty string as a valid sequence" in {
    assert(LatinNumerics.valid(""))
    assert(LatinNumerics.numericToInt("") == 0)
  }
/*
  it should "accept any single digital as a valid sequence" in {
    assert(LatinNumerics.valid(s"${LatinNumerics.two}"))
    assert(LatinNumerics.valid(s"${LatinNumerics.thousand}"))
  }

  it should "recognize a subtractive combination of 40 or 90" in {
    val forty = s"${LatinNumerics.ten}${LatinNumerics.fifty}"
    assert(LatinNumerics.valid(forty))
    assert(LatinNumerics.numericToInt(forty) == 40)

    val ninety = s"${LatinNumerics.ten}${LatinNumerics.hundred}"
    assert(LatinNumerics.valid(ninety))
    assert(LatinNumerics.numericToInt(ninety) == 90)
  }

  it should "recognize extensions of subtractive combinations" in {
    val fortyTwo = s"${LatinNumerics.ten}${LatinNumerics.fifty}${LatinNumerics.two}"
    assert(LatinNumerics.valid(fortyTwo))
    assert(LatinNumerics.numericToInt(fortyTwo) == 42)

    val ninetyFive = s"${LatinNumerics.ten}${LatinNumerics.hundred}${LatinNumerics.five}"
    assert(LatinNumerics.valid(ninetyFive))
    assert(LatinNumerics.numericToInt(ninetyFive) == 95)

  }
*/
  it should "recognize even tens" in {
    val eighty = s"${LatinNumerics.fifty}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}"
    assert(LatinNumerics.valid(eighty))
    assert(LatinNumerics.numericToInt(eighty) == 80)
  }

  it should "reject additive forty and ninety" in {
    val badForty = s"${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}"
    assert(LatinNumerics.valid(badForty) == false)
    assert(LatinNumerics.numericToInt(badForty) == 40)
  }

}
