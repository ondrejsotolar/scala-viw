package viw.mandatory

import org.scalatest.{BeforeAndAfter, FunSuite}
import viw.ViwTest

class MoveLeftTest extends FunSuite with ViwTest with BeforeAndAfter {

  def br: String = sys.props("line.separator")

  val sourceText_1 =
    "Lorem" + br +
      "Lorem2" + br +
      "ipsum dolor sit ame#t# " + br +
      "Cras qiet.idunt."


  test("Cursor move left one, line center") {
    val expected =
      "Lorem" + br +
        "Lorem2" + br +
        "ipsum dolor sit am#e#t " + br +
        "Cras qiet.idunt."
    viwTrue("h", sourceText_1, expected)
  }

  test("Cursor move left n, line center") {
    val expected =
      "Lorem" + br +
        "Lorem2" + br +
        "#i#psum dolor sit amet " + br +
        "Cras qiet.idunt."
    viwTrue("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh", sourceText_1, expected)
  }

  test("Cursor move left n, line start") {
    val expected =
      "Lorem" + br +
        "Lorem2" + br +
        "#i#psum dolor sit amet " + br +
        "Cras qiet.idunt."
    viwTrue("hhhhhhhhhhhhhhhhhhhhhhhhhh", expected, expected)
  }

  test("Cursor move left n, file start") {
    val expected =
      "#L#orem" + br +
        "Lorem2" + br +
        "ipsum dolor sit amet " + br +
        "Cras qiet.idunt."
    viwTrue("hhhhhhhhhhhhhhhhhhhhhhhhhh", expected, expected)
  }

  test("Cursor move left n, empty line") {
    val expected =
      "Lorem" + br +
        "##" + br +
        "ipsum dolor sit amet " + br +
        "Cras qiet.idunt."
    viwTrue("hhhhhhhhhhhhhhhhhhhhhhhhhh", expected, expected)
  }
}
