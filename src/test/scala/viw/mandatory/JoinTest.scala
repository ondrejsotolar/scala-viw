package viw.mandatory
import org.scalatest.FunSuite
import viw.{ViwTest}

class JoinTest extends FunSuite with ViwTest {

  test("Join line") {
    viwTrue(
      "J",
      """Lorem #i#psum elit
        |dolor sit amet
        |dolor sit amet""".stripMargin,
      """Lorem ipsum elit# #dolor sit amet
        |dolor sit amet""".stripMargin
    )
  }

  test("Join line - multi line last line") {
    viwTrue(
      "J",
      """Lorem ipsum elit
        |dolor si#t# amet""".stripMargin,
      """Lorem ipsum elit
        |dolor si#t# amet""".stripMargin
    )
  }

  test("Join line - extended end") {
    viwTrue(
      "J",
      """Lorem ipsum elit##
        |dolor sit amet""".stripMargin,
      """Lorem ipsum elit# #dolor sit amet""".stripMargin
    )
  }

  test("Join - empty file") {
    viwTrue(
      "J",
      """##""",
      """##"""
    )
  }

}
