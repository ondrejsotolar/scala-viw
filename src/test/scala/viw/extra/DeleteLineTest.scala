package viw.extra

import org.scalatest.FunSuite
import viw.{ViwTest}

class DeleteLineTest extends FunSuite with ViwTest {

  test("Delete whole line") {
    viwTrue(
      "dd",
      """Lorem ipsum dolor
        |sit a#m#et
        |consectetur adipiscing elit""".stripMargin,
      """Lorem ipsum dolor
        |#c#onsectetur adipiscing elit""".stripMargin,
    )
  }

  test("Delete whole line - one line") {
    viwTrue(
      "dd",
      """consecte#t#ur adipiscing elit""",
      """##"""
    )
  }

  test("Delete whole line - on empty line") {
    viwTrue(
      "dd",
      """Lorem ipsum elit
        |##
        |consectetur adipiscing elit""".stripMargin,
      """Lorem ipsum elit
        |#c#onsectetur adipiscing elit""".stripMargin
    )
  }

  test("Delete whole line - behind last char") {
    viwTrue(
      "dd",
      """Lorem ipsum elit##
        |consectetur adipiscing elit""".stripMargin,
      """#c#onsectetur adipiscing elit""".stripMargin
    )
  }

  test("Delete whole line - shorter, cursor 0") {
    viwTrue(
      "dd",
      """Lorem ipsum elit
        |consectetur a#d#ipiscing elit""".stripMargin,
      """#L#orem ipsum elit""".stripMargin
    )
  }

  test("Delete whole line - longer, cursor 0") {
    viwTrue(
      "dd",
      """Lorem ipsum elit
        |consectetur adipiscing e#l#it""".stripMargin,
      """#L#orem ipsum elit""".stripMargin
    )
  }

  test("Delete whole line - empty file") {
    viwTrue(
      "dd",
      """##""",
      """##"""
    )
  }

  test("Delete whole line - repeat") {
    viwTrue(
      "dddd",
      """Lorem ipsum dolor
        |consectetur adipi#s#cing elit
        |consectetur adipiscing elit
        |consectetur adipiscing elit""".stripMargin,
      """Lorem ipsum dolor
        |#c#onsectetur adipiscing elit""".stripMargin,
    )
  }
}
