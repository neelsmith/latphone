package edu.holycross.shot.latin


/** Object for working with strings of numeric characters.
*/
object LatinNumerics {
  // basic chars
  /** Unicode Roman numeral 1 */
  val one = '\u2160'
  /** Unicode Roman numeral 2 */
  val two =  '\u2161'
  /** Unicode Roman numeral 3 */
  val three =  '\u2162'
  /** Unicode Roman numeral 4 */
  val four =  '\u2163'
  /** Unicode Roman numeral 5 */
  val five = '\u2164'
  /** Unicode Roman numeral 6 */
  val six = '\u2165'
  /** Unicode Roman numeral 7 */
  val seven = '\u2166'
  /** Unicode Roman numeral 8 */
  val eight =  '\u2167'
  /** Unicode Roman numeral 9 */
  val nine = '\u2168'
  /** Unicode Roman numeral 10 */
  val ten = '\u2169'
  /** Unicode Roman numeral 50 */
  val fifty = '\u216C'
  /** Unicode Roman numeral 100 */
  val hundred =  '\u216D'
  /** Unicode Roman numeral 500 */
  val fiveHundred = '\u216E'
  /** Unicode Roman numeral 1000 */
  val thousand = '\u216F'
  /** Unicode Roman numeral 5000 */
  val fiveThousand = '\u2181'
  /** Unicode Roman numeral 10000 */
  val tenThousand = '\u2182'

  // substractive strings
  /** Subtractive string for Roman numeral 40 */
  val forty = s"${ten}${fifty}"
  /** Subtractive string for Roman numeral 90 */
  val ninety = s"${ten}${hundred}"

  /** Subtractive string for Roman numeral 400 */
  val fourHundred = s"${hundred}${fiveHundred}"
  /** Subtractive string for Roman numeral 900 */
  val nineHundred = s"${hundred}${thousand}"


  /** Subtractive pairs.
  */
  val subtractives: Vector[String] = Vector(
    forty,
    ninety,
    fourHundred,
    nineHundred
  )

  val additiveTens = Vector(10,20,30,50,60,70,80)
  val additiveHundreds = Vector(100,200,300,500,600,700,800)

  /** True if string begins with a subtractive sequence.
  *
  * @param s String to check.
  */
  def subtractive(s: String): Boolean = {
    if (s.size < 2) {
      false
    } else {
      subtractives.contains(s.slice(0,2))
    }
  }

  /** Determine if a string of numeric characters obeys rules
  * of sequence.
  *
  * @param s String to evaluate.
  * @param lastSeen Integer value of last character seen.
  */
  def valid(src: String, cumulation: String = "", lastSeen: Int = 0): Boolean = {
    println(s"VALIDATING ${src}, ${cumulation}, ${lastSeen}")
      src.size match {
        case 0  => true
        case _ => {
          println("WORK ON  " + src + " and lastSeen " + lastSeen)
          if (subtractive(src)) {
            val sliver = src.slice(0,2)
            //println("Continue subtractive with " + sliver)
            valid(src.tail.tail, cumulation + sliver, lastSeen + numericToInt(sliver))

          } else if (lastSeen == 0) {
            valid(src.tail, cumulation + src.head, lastSeen + numericToInt(src.head))

          } else {
            println("Match lastSeen " + lastSeen)
            lastSeen match {
              case thou if (thou % 1000 == 0) => {
                println("It's a thousand!")
                false
              }
              case hund if (hund % 100 == 0)=> {
                val  nextDigit =  numericToInt(src.head)
                println("Hundred! with next digit " + nextDigit)
                nextDigit match {
                  case ones if 1 to 9 contains ones =>  valid(src.tail, cumulation + src.head, numericToInt(src.head))
                  case tens if additiveTens.contains(tens) =>  valid(src.tail, cumulation + src.head, numericToInt(src.head))
                  case hundreds if additiveHundreds.contains(hundreds) =>  valid(src.tail, cumulation + src.head, numericToInt(src.head))
                  case _ => false
                }
              }

              case ten if (ten % 10 == 0)=> {
                val  nextDigit =  numericToInt(src.head)
                println("Ten! with next digit " + nextDigit)
                nextDigit match {
                  case ones if 1 to 9 contains ones =>
                  valid(src.tail, cumulation + src.head, numericToInt(src.head))

                  case 10 => {
                    if (additiveTens.contains(LatinNumerics.numericToInt(cumulation + src.head))) {
                      val newTotal = LatinNumerics.numericToInt(cumulation + src.head)
                      println(s"and ${newTotal} is in additive tens...")
                      valid(src.tail, cumulation + src.head, numericToInt(src.head))
                    } else { false }
                  }

                  case _ => false
                }
              }

              case i: Int => {
                println("=>NOT HANDLED: division into place value of " + i)
                false
              }
            }
          }
        }
      }
    }



  /** Convert numeric character to corresponding integer value.
  * It is an exception if c is not a numeric character.
  *
  * @param c Numeric character to convert.
  */
  def numericToInt(c: Char) : Int = {
     c match {
      case LatinNumerics.one => 1
      case LatinNumerics.two  => 2
      case LatinNumerics.three => 3
      case LatinNumerics.four => 4
      case LatinNumerics.five => 5
      case LatinNumerics.six => 6
      case LatinNumerics.seven => 7
      case LatinNumerics.eight => 8
      case LatinNumerics.nine => 9
      case LatinNumerics.ten => 10
      case LatinNumerics.fifty => 50
      case LatinNumerics.hundred => 100
      case LatinNumerics.fiveHundred => 500
      case LatinNumerics.thousand => 1000
      case LatinNumerics.fiveThousand => 5000
      case LatinNumerics.tenThousand => 10000
      case _ => throw new Exception(s"LatinNumerics: ${c} is not a valid numeric character.")
    }
  }

  /** Sum up the integer values of a string of numeric characters, without
  * validating syntax of numeric expression, other than requiring that
  * each character of s must be a numeric character.
  *
  * @param s String of numeric characters to sum up.
  */
  def numericToInt(s: String, total: Int = 0) : Int = {
    if (s.size == 0) {
      total
    } else if (s.contains(LatinNumerics.forty)) {
      numericToInt(s.replaceFirst(LatinNumerics.forty,""), total + 40)
    } else if (s.contains(LatinNumerics.ninety)){
      numericToInt(s.replaceFirst(LatinNumerics.ninety,""), total + 90)
    } else if (s.size == 1) {
      total + numericToInt(s.head)

    } else {
      val subTotal = total + numericToInt(s.head)
      numericToInt(s.tail, subTotal)
    }
  }

}
