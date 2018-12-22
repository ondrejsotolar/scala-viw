package viw.mandatory
import org.scalatest.FunSuite
import viw.{ViwTest}

class LineEnd extends FunSuite with ViwTest {
  test("End of line - $") {
    viwTrue(
      "$",
      """Lorem ipsum dolor sit.am#e#t, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > ye#s#
        |  !.!  dolor sit amet""".stripMargin,
    )
  }

  test("End of line - $ - last line") {
    viwTrue(
      "$",
      """Lorem ipsum dolor sit.amet, c  > yes
        |  !.!  dolo#r# sit amet""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit ame#t#""".stripMargin,
    )
  }

  test("Start of line - 0") {
    viwTrue(
      "0",
      """Lorem ipsum dolor sit.am#e#t, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
      """#L#orem ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
    )
  }
}