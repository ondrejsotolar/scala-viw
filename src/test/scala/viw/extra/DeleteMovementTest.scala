package viw.extra

import org.scalatest.FunSuite
import viw.ViwTest

class DeleteMovementTest extends FunSuite with ViwTest {

  test("Delete + h - left") {
    viwTrue(
      "dh",
      """Lorem ipsum dolor
        |consectetur adipiscing e#l#it
        |consectetur adipiscing elit""".stripMargin,
      """Lorem ipsum dolor
        |consectetur adipiscing #l#it
        |consectetur adipiscing elit""".stripMargin,
    )
  }

  test("Delete + h - left on first char - do nothing") {
    viwTrue(
      "dh",
      """Lorem ipsum dolor
        |#c#onsectetur adipiscing elit
        |third line""".stripMargin,
      """Lorem ipsum dolor
        |#c#onsectetur adipiscing elit
        |third line""".stripMargin,
    )
  }

  test("Delete + h - start of file") {
    viwTrue(
      "dh",
      """#L#orem ipsum dolor
        |consectetur adipiscing elit
        |consectetur adipiscing elit""".stripMargin,
      """#L#orem ipsum dolor
        |consectetur adipiscing elit
        |consectetur adipiscing elit""".stripMargin,
    )
  }

  test("Delete + l - right") {
    viwTrue(
      "dl",
      """Lorem ipsum dolor
        |consectetur #a#dipiscing elit
        |consectetur adipiscing elit""".stripMargin,
      """Lorem ipsum dolor
        |consectetur #d#ipiscing elit
        |consectetur adipiscing elit""".stripMargin,
    )
  }

  test("Delete + l - on last char") {
    viwTrue(
      "dl",
      """Lorem ipsum dolor
        |consectetur dipiscing eli#t#
        |consectetur adipiscing elit""".stripMargin,
      """Lorem ipsum dolor
        |consectetur dipiscing el#i#
        |consectetur adipiscing elit""".stripMargin,
    )
  }

  test("Delete + l - empty line - do nothing") {
    viwTrue(
      "dl",
      """Lorem ipsum dolor
        |##
        |consectetur adipiscing elit""".stripMargin,
      """Lorem ipsum dolor
        |##
        |consectetur adipiscing elit""".stripMargin,
    )
  }

  test("Delete + l - end of file") {
    viwTrue(
      "dl",
      """Lorem ipsum dolor
        |consectetur dipiscing eli#t#""".stripMargin,
      """Lorem ipsum dolor
        |consectetur dipiscing el#i#""".stripMargin,
    )
  }

  test("Delete + k - up - first line") {
    viwTrue(
      "dk",
      """Lorem#d# line
        |consectetur dipiscing el""".stripMargin,
      """Lorem#d# line
        |consectetur dipiscing el""".stripMargin,
    )
  }

  test("Delete + k - up - start of file") {
    viwTrue(
      "dk",
      """#L#orem line
        |consectetur dipiscing el""".stripMargin,
      """#L#orem line
        |consectetur dipiscing el""".stripMargin,
    )
  }

  test("Delete + k - up") {
    viwTrue(
      "dk",
      """Lorem line
        |L#o#rem line
        |consectetur dipiscing el""".stripMargin,
      """#c#onsectetur dipiscing el""".stripMargin,
    )
  }

  test("Delete + j") {
    viwTrue(
      "dj",
      """Lorem #i#psum dolor
        |2consectetur dipiscing el
        |consectetur dipiscing el""".stripMargin,
      """#c#onsectetur dipiscing el""".stripMargin
    )
  }

  test("Delete + j - last line ") {
    viwTrue(
      "dj",
      """consectetur dipiscing el
        |consectetur dipiscing el
        |consectetur dipis#c#ing el""".stripMargin,
      """consectetur dipiscing el
        |consectetur dipiscing el
        |consectetur dipis#c#ing el""".stripMargin,
    )
  }

//  test("Delete + w - next word") {
//    viwTrue(
//      "dw",
//      """Lorem i#p#sum dolor
//        |consectetur dipiscing el
//        |consectetur dipiscing el""".stripMargin,
//      """Lorem i#p#olor
//        |consectetur dipiscing el
//        |consectetur dipiscing el""".stripMargin,
//    )
//  }

//  test("Delete + w - repeat") {
//    viwTrue(
//      "dwdw",
//      """Lorem i#p#sum dolor
//        |consectetur dipiscing el
//        |consectetur dipiscing el""".stripMargin,
//      """Lorem i#p#econd line
//        |consectetur dipiscing el""".stripMargin,
//    )
//  }

//  test("Delete + movement - backWord") {
//    viwTrue(
//      "db",
//      """Lorem i#p#sum dolor
//        |second line
//        |third line""".stripMargin,
//      """Lorem #p#sum dolor
//        |second line
//        |third line""".stripMargin,
//    )
//  }

//  test("Delete + movement - backWord - to previous word") {
//    viwTrue(
//      "db",
//      """Lorem #i#psum dolor
//        |second line
//        |third line""".stripMargin,
//      """#i#psum dolor
//        |second line
//        |third line""".stripMargin,
//    )
//  }
//
//  test("Delete + movement - endOfWord") {
//    viwTrue(
//      "de",
//      """Lorem i#p#sum dolor
//        |second line
//        |third line""".stripMargin,
//      """Lorem i#p# dolor
//        |second line
//        |third line""".stripMargin,
//    )
//  }
//
//  test("Delete + movement - endOfLine") {
//    viwTrue(
//      "d$",
//      """Lorem i#p#sum dolor
//        |second line
//        |third line""".stripMargin,
//      """Lorem i#p#
//        |second line
//        |third line""".stripMargin,
//    )
//  }
//
//  test("Delete + movement - startOfLine") {
//    viwTrue(
//      "d0",
//      """Lorem i#p#sum dolor
//        |second line
//        |third line""".stripMargin,
//      """#p#sum dolor
//        |second line
//        |third line""".stripMargin,
//    )
//  }
//
//  test("Delete + movement - matchingBrackets") {
//    viwTrue(
//      "d%",
//      """Lorem i#[#sum dolor
//        |second line
//        |third ]line""".stripMargin,
//      """Lorem i#[#line""".stripMargin,
//    )
//  }

}
