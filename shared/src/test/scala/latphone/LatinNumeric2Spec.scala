package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinNumeric2Spec extends FlatSpec {


  "The LatinNumerics object" should "accept an empty string as a valid sequence" in {
    assert(LatinNumerics.valid(""))
    assert(LatinNumerics.numericToInt("") == 0)
  }

  it should "accept any single digit as a valid sequence" in {
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

  it should "recognize extensions of subtractive combinations of 40 or 90" in {
    val fortyTwo = s"${LatinNumerics.ten}${LatinNumerics.fifty}${LatinNumerics.two}"
    assert(LatinNumerics.valid(fortyTwo))
    assert(LatinNumerics.numericToInt(fortyTwo) == 42)

    val ninetyFive = s"${LatinNumerics.ten}${LatinNumerics.hundred}${LatinNumerics.five}"
    assert(LatinNumerics.valid(ninetyFive))
    assert(LatinNumerics.numericToInt(ninetyFive) == 95)
  }

  it should "recognize even tens" in {
    val eighty = s"${LatinNumerics.fifty}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}"
    assert(LatinNumerics.valid(eighty))
    assert(LatinNumerics.numericToInt(eighty) == 80)
  }

  it should "recognize extensions of even tens" in {
      val seventyOne = s"${LatinNumerics.fifty}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.one}"
      assert(LatinNumerics.valid(seventyOne))
      assert(LatinNumerics.numericToInt(seventyOne) == 71)
  }

  it should "reject additive forty and ninety" in {
    val badForty = s"${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}"
    assert(LatinNumerics.valid(badForty) == false)
    assert(LatinNumerics.numericToInt(badForty) == 40)

    val badFortyTwo = s"${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.ten}${LatinNumerics.two}"
    assert(LatinNumerics.valid(badFortyTwo) == false)
    assert(LatinNumerics.numericToInt(badFortyTwo) == 42)
  }

  it should "recognize even hundreds" in {
      val threeHundred = s"${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}"
      assert(LatinNumerics.valid(threeHundred))
      assert(LatinNumerics.numericToInt(threeHundred) == 300)
  }

  it should "recognize extensions of even hundreds" in {
      val threeOhOne = s"${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.one}"
      assert(LatinNumerics.valid(threeOhOne))
      assert(LatinNumerics.numericToInt(threeOhOne) == 301)

      val threeEleven = s"${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.ten}${LatinNumerics.one}"
      assert(LatinNumerics.valid(threeEleven))
      assert(LatinNumerics.numericToInt(threeEleven) == 311)
  }

  it should "recognize a subtractive combination of 400 or 900" in {
    assert(LatinNumerics.valid(LatinNumerics.fourHundred))
    assert(LatinNumerics.numericToInt(LatinNumerics.fourHundred) == 400)

    assert(LatinNumerics.valid(LatinNumerics.nineHundred))
    assert(LatinNumerics.numericToInt(LatinNumerics.nineHundred) == 900)
  }

  it should "recognize extensions of subtractive combinations of 400 or 900" in {
    val fourOhTwo = s"${LatinNumerics.hundred}${LatinNumerics.fiveHundred}${LatinNumerics.two}"
    assert(LatinNumerics.valid(fourOhTwo))
    assert(LatinNumerics.numericToInt(fourOhTwo) == 402)

    val fourFourtyFive = s"${LatinNumerics.hundred}${LatinNumerics.fiveHundred}${LatinNumerics.ten}${LatinNumerics.fifty}${LatinNumerics.five}"
    assert(LatinNumerics.valid(fourFourtyFive))
    assert(LatinNumerics.numericToInt(fourFourtyFive) == 445)
  }

  it should "reject additive 400 and 900" in {
    val badFourHundred = s"${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}"
    assert(LatinNumerics.valid(badFourHundred) == false)
    assert(LatinNumerics.numericToInt(badFourHundred) == 400)

    val badFourFortyTwo = s"${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.ten}${LatinNumerics.fifty}${LatinNumerics.two}"
    assert(LatinNumerics.valid(badFourFortyTwo) == false)
    assert(LatinNumerics.numericToInt(badFourFortyTwo) == 442)
  }

  it should "reject badly sequenced values" in {
    val badTwoFiftyTwo = s"${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.two}${LatinNumerics.fifty}"
    assert(LatinNumerics.valid(badTwoFiftyTwo) == false)
    assert(LatinNumerics.numericToInt(badTwoFiftyTwo) == 252)
  }
}
