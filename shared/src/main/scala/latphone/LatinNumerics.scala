package edu.holycross.shot.latin


/** Object for working with strings of numeric characters.
*/
object LatinNumerics {
  // basic chars
  val one = '\u2160'
  val two =  '\u2161'
  val three =  '\u2162'
  val four =  '\u2163'
  val five = '\u2164'
  val six = '\u2165'
  val seven = '\u2166'
  val eight =  '\u2167'
  val nine = '\u2168'
  val ten = '\u2169'


  val fifty = '\u216C'
  val hundred =  '\u216D'
  val fiveHundred = '\u216E'
  val thousand = '\u216F'
  val fiveThousand = '\u2181'
  val tenThousand = '\u2182'

  // substractive strings
  val forty = s"${ten}${fifty}"
  val ninety = s"${ten}${hundred}"

  /** Determine if a string of numeric characters obeys rules
  * of sequence.
  *
  * @param s String to evaluate.
  * @param lastSeen Integer value of last character seen.
  */
  def valid(src: String, cumulation: String = "", lastSeen: Int = 0): Boolean = {
    src.size match {
      case 0  => true
      case 1 =>  {(lastSeen == 0) || (lastSeen > numericToInt(src.head))}
      case _ => {
        if ((lastSeen == 0) || (lastSeen > numericToInt(src.head))) {
          valid(src.tail, cumulation + src.head, numericToInt(src.head))
        } else {
          lastSeen match {

            case i if 0 to 9 contains i => (lastSeen > 10)
            case 10 => {false}
            case 50 => {false}
            case 100 => {false}
            case 500 => {false}
            case 1000 => {false}
            case 5000 => {false}
            case 10000 => {false}
          }
        }
      }
    }
  }
    /*
    case 1 =>  (lastSeen == 0) || (lastSeen >  numericToInt(src.head))
    case _ => {
      //val prevDigit =
      false
    }

    // Multipe charaters to evaluate:
    } else {
      lastSeen match {

        case i if 0 to 9 contains i => (lastSeen > 10)
        case 10 => {false}
        case 50 => {false}
        case 100 => {false}
        case 500 => {false}
        case 1000 => {false}
        case 5000 => {false}
        case 10000 => {false}
      }
    }*/



/*
    } else if (lastSeen == 0) {
      valid(s.tail, numericToInt(s.head))

    } else {
      val currCharVal = numericToInt(s.head)
      println("currCharVal == " + currCharVal)
      false
    }*/



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

  /** Sum up the integer values of a string of numeric characters..
  * It is an exception if any character of s is not a numeric character.
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
