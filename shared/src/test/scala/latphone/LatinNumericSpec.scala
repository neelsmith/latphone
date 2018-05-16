package edu.holycross.shot.latin
import org.scalatest.FlatSpec




class LatinNumericSpec extends FlatSpec {

  val fourteen = s"${LatinNumerics.ten}${LatinNumerics.four}"

  "The LatinNumerics object" should "accept an empty string as a valid sequence" in {
    assert(LatinNumerics.valid(""))
  }
  it should "interpret an empty string as numeric zero" in {
    assert(LatinNumerics.numericToInt("") == 0)
  }

  it should "accept values from ten to 20 when digits decrease in size" in  {
    assert(LatinNumerics.valid(fourteen))
  }
  it should "reject multiple 1-digit code points" in {
    val badNumber = s"${LatinNumerics.one}${LatinNumerics.two}"
    assert(LatinNumerics.valid(badNumber) == false)
  }

  it should "accept values from 20 to 100 when digits decrease in size" in  {
    val onehundred11 = s"${LatinNumerics.hundred}${LatinNumerics.ten}${LatinNumerics.one}"
    assert(LatinNumerics.valid(onehundred11))
  }
  it should "accept duplicated ten values for 20, 30, 70 and 80" in {
    assert(LatinNumerics.valid(s"${LatinNumerics.ten}"))
    assert(LatinNumerics.valid(s"${LatinNumerics.ten}${LatinNumerics.ten}"))
  }

  it should "accept subtractive values for 90" in pending

  it should "accept values from 100 to 1000 when digits decrease in size" in  pending /*{
    val fivehundred11 = s"${LatinNumerics.fiveHundred}${LatinNumerics.ten}${LatinNumerics.one}"
    assert(LatinNumerics.valid(fivehundred11))

    val thousand111 = s"${LatinNumerics.thousand}${LatinNumerics.hundred}${LatinNumerics.ten}${LatinNumerics.one}"
    assert(LatinNumerics.valid(thousand111))
  }*/
  it should "accept duplicated ten values for 200, 300, 700 and 800" in pending
  it should "accept subtractive values for 400" in pending
  it should "accept subtractive values for 900" in pending



  it should "convert individual numeric chars to integer values"  in {
    val ten = '\u2169'
    assert(LatinNumerics.numericToInt(ten) == 10)
  }

  it should "convert strings of numeric chars to integer values" in {
    assert(LatinNumerics.numericToInt(fourteen) == 14)
  }


  it should "recognize subtractive patterns combined with other characrters" in {
    val fortyone = s"${LatinNumerics.forty}${LatinNumerics.one}"
    assert (LatinNumerics.numericToInt(fortyone) == 41)
  }


  it should "convert individual tens chars to integer values"  in {
    val ten = '\u2169'
    assert(LatinNumerics.numericToInt(ten) == 10)
  }


  it should "recognize subtractive patterns for 40 and 90" in {
    assert(LatinNumerics.numericToInt(LatinNumerics.forty) == 40)
    assert(LatinNumerics.numericToInt(LatinNumerics.ninety) == 90)
  }




}
