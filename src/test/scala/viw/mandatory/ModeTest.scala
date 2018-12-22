package viw.mandatory

import org.scalatest.FunSuite
import viw.{ViwTest}

class ModeTest extends FunSuite with ViwTest {
  test("Append") {
    viwFalse(
      "a",
      "x#y#z",
      "xy#z#",
    )
  }

  test("Append last char") {
    viwFalse(
      "a",
      "xy#z#",
      "xyz##",
    )
  }

  test("Open") {
    viwFalse(
      "o",
      "x#y#z",
      """xyz
        |##""".stripMargin,
    )
  }

  test("Open - more lines enter") {
    viwFalse(
      "o",
      """xyz
        |a#b#c
        |klm""".stripMargin,
      """xyz
        |abc
        |##
        |klm""".stripMargin,
    )
  }

  test("Substitute") {
    viwFalse(
      "s",
      "x#y#z",
      """x#z#""".stripMargin,
    )
  }

  test("Substitute - line last char") {
    viwFalse(
      "s",
      """Lorem ipsum eli#t#
        |sti amet""".stripMargin,
      """Lorem ipsum eli##
        |sti amet""".stripMargin
    )
  }

  test("Substitute - last line char one more") {
    viwTrue(
      "s",
      """Lorem ipsum elit##
        |sit amet""".stripMargin,
      """Lorem ipsum elit##
        |sit amet""".stripMargin
    )
  }

  test("Substitute - eof") {
    viwFalse(
      "s",
      """Lorem ipsum do#r#""",
      """Lorem ipsum do##"""
    )
  }

  test("Substitute - start of ln") {
    viwFalse(
      "s",
      """xyz
        |#a#bc
        |klm""".stripMargin,
      """xyz
        |#b#c
        |klm""".stripMargin,
    )
  }

  test("Substitute - ln length = 1") {
    viwFalse(
      "s",
      """xyz
        |#m#""".stripMargin,
      """xyz
        |##""".stripMargin,
    )
  }

  test("Substitute - blank line") {
    viwTrue(
      "s",
      """xyz
        |##""".stripMargin,
      """xyz
        |##""".stripMargin,
    )
  }

  test("Substitute - blank file") {
    viwTrue(
      "s",
      """##""".stripMargin,
      """##""".stripMargin,
    )
  }

  test("Go - cursor on last line") {
    viwFalse(
      "G",
      "a#b#c",
      """#a#bc""".stripMargin,
    )
  }

  test("Go") {
    viwFalse(
      "G",
      """Lorem ipsum elit##
        |sit amet""".stripMargin,
      """Lorem ipsum elit
        |#s#it amet""".stripMargin
    )
  }

  test("Go - eof") {
    viwFalse(
      "G",
      """Lorem ipsum dolo#r#""",
      """#L#orem ipsum dolor"""
    )
  }

  test("Go - empty file") {
    viwFalse(
      "G",
      """##""".stripMargin,
      """##""".stripMargin,
    )
  }

  test("Insert in line") {
    viwFalse(
      "I",
      """Lorem ipsum.dolor sit amet, consectetur adipiscing elit  />\
        |  .?  secondTestwor#d# with some extra fluff""".stripMargin,
      """Lorem ipsum.dolor sit amet, consectetur adipiscing elit  />\
        |# # .?  secondTestword with some extra fluff""".stripMargin
    )
  }

  test("Insert in line - first line") {
    viwFalse(
      "I",
      """Lorem ipsum.dolor si#t# amet, consectetur adipiscing elit  />\
        |  .?  secondTestword with some extra fluff""".stripMargin,
      """#L#orem ipsum.dolor sit amet, consectetur adipiscing elit  />\
        |  .?  secondTestword with some extra fluff""".stripMargin
    )
  }

  test("Insert in line - empty line") {
    viwFalse(
      "I",
      """##
        |  .?  secondTestword with some extra fluff""".stripMargin,
      """##
        |  .?  secondTestword with some extra fluff""".stripMargin
    )
  }

  test("Insert in line - empty file") {
    viwFalse(
      "I",
      """##""".stripMargin,
      """##""".stripMargin
    )
  }

  test("Insert after line - blank file") {
    viwFalse(
      "A",
      """##""".stripMargin,
      """##""".stripMargin
    )
  }

  test("Change line - cursor behind last character of line") {
    viwFalse(
      "C",
      """Lorem ipsum elit##
        |sit amet""".stripMargin,
      """Lorem ipsum elit##
        |sit amet""".stripMargin
    )
  }

  test("Change line - empty file") {
    viwFalse(
      "C",
      """##""".stripMargin,
      """##""".stripMargin
    )
  }
}
