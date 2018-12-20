package viw.cursor
import org.scalatest.FunSuite
import viw.{ViwTest}

class MoveDown extends FunSuite with ViwTest {
  test("Move down - j") {
    viwTrue(
      "j",
      """Lore#m# ipsum
        |Dolor sit amet""".stripMargin,
      """Lorem ipsum
        |Dolo#r# sit amet""".stripMargin,
    )
  }

  test("Move down - j - last char") {
    viwTrue(
      "j",
      """Lorem ipsum dolor sit ame#t#
        |Dolor sit amet""".stripMargin,
      """Lorem ipsum dolor sit amet
        |Dolor sit ame#t#""".stripMargin,
    )
  }

  test("Move down - j - behind last char") {
    viwTrue(
      "j",
      """Lorem ipsum dolor##
        |sit amet""".stripMargin,
      """Lorem ipsum dolor
        |sit ame#t#""".stripMargin,
    )
  }

  test("Move down - j - last line") {
    viwTrue(
      "j",
      """Lorem ipsum dolor sit amet
        |Dolor sit ame#t#""".stripMargin,
      """Lorem ipsum dolor sit amet
        |Dolor sit ame#t#""".stripMargin,
    )
  }
}
