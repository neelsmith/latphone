package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinThousandsSpec extends FlatSpec {


  "The LatinNumerics object" should "recognize even thousands" in {
      val threeThousand = s"${LatinNumerics.thousand}${LatinNumerics.thousand}${LatinNumerics.thousand}"
      assert(LatinNumerics.valid(threeThousand) == true)
      assert(LatinNumerics.numericToInt(threeThousand) == 3000)
  }

  it should "recognize extensions of even thousands" in {
/*      val threeThousandOne = s"${LatinNumerics.thousand}${LatinNumerics.thousand}${LatinNumerics.thousand}${LatinNumerics.one}"
      assert(LatinNumerics.valid(threeThousandOne))
      assert(LatinNumerics.numericToInt(threeThousandOne) == 3001)

      val threeThousandEleven = s"${LatinNumerics.thousand}${LatinNumerics.thousand}${LatinNumerics.thousand}${LatinNumerics.ten}${LatinNumerics.one}"
      assert(LatinNumerics.valid(threeThousandEleven))
      assert(LatinNumerics.numericToInt(threeThousandEleven) == 3011)
*/

      val thirtyThreeEleven = s"${LatinNumerics.thousand}${LatinNumerics.thousand}${LatinNumerics.thousand}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.hundred}${LatinNumerics.ten}${LatinNumerics.one}"
      assert(LatinNumerics.valid(thirtyThreeEleven))
      assert(LatinNumerics.numericToInt(thirtyThreeEleven) == 3311)
  }
/*
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
  */
}
