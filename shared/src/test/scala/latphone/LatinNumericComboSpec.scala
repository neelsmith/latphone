package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinNumericComboSpec extends FlatSpec {

  val fourteen = s"${LatinNumerics.ten}${LatinNumerics.four}"

  "The LatinNumerics object" should "accept duplicated ten values for 20, 30, 70 and 80" in {
    val ten = LatinNumerics.ten
    assert(LatinNumerics.valid(s"${ten}"))
    assert(LatinNumerics.numericToInt(ten) == 10)

    val twenty = s"${LatinNumerics.ten}${LatinNumerics.ten}"
    assert(LatinNumerics.valid(twenty))
    assert(LatinNumerics.numericToInt(twenty) == 20)

    val seventy = s"${LatinNumerics.fifty}${LatinNumerics.ten}${LatinNumerics.ten}"
    assert(LatinNumerics.valid(seventy))
    assert(LatinNumerics.numericToInt(seventy) == 70)

    val seventyTwo = s"${LatinNumerics.fifty}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.two}"
    assert(LatinNumerics.valid(seventyTwo))
    assert(LatinNumerics.numericToInt(seventyTwo) == 72)
  }

  it should "reject additive values for 40" in {
    val badForty = s"${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}"
    assert(LatinNumerics.valid(badForty) == false)
    assert(LatinNumerics.numericToInt(badForty) == 40)
  }

  it should "reject additive values for 90" in {
    val badNinety = s"${LatinNumerics.fifty}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}"
    assert(LatinNumerics.valid(badNinety) == false)
    assert(LatinNumerics.numericToInt(badNinety) == 90)
  }

  it should "accept duplicated hundreds values for 100 to  300" in {
    val oneFiftyTwo = s"${LatinNumerics.hundred}${LatinNumerics.fifty}${LatinNumerics.two}"
    assert(LatinNumerics.valid(oneFiftyTwo))
    assert(LatinNumerics.numericToInt(oneFiftyTwo) == 152)

    val twoFiftyTwo = s"${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.fifty}${LatinNumerics.two}"
    assert(LatinNumerics.valid(twoFiftyTwo))
    assert(LatinNumerics.numericToInt(twoFiftyTwo) == 252)
  }
  it should "accept duplicated hundreds values for 500 to 900" in {
    assert(LatinNumerics.valid(s"${LatinNumerics.fiveHundred}"))
    assert(LatinNumerics.valid(s"${LatinNumerics.fiveHundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}"))

    val eightFiftyTwo = s"${LatinNumerics.fiveHundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.fifty}${LatinNumerics.two}"
    assert(LatinNumerics.valid(eightFiftyTwo))
    assert(LatinNumerics.numericToInt(eightFiftyTwo) == 852)
  }

  it should "reject additive values for 900" in {
    val badNineHundred = s"${LatinNumerics.fiveHundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}"
    assert(LatinNumerics.valid(badNineHundred) == false)
    assert(LatinNumerics.numericToInt(badNineHundred) == 900)
  }

}
