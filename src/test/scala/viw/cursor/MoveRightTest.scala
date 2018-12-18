package viw.cursor

import org.scalatest.{BeforeAndAfter, FunSuite}
import viw.ViwTest

class MoveRightTest extends FunSuite with ViwTest with BeforeAndAfter {

  def br: String = sys.props("line.separator")

  val sourceText_1 =
    "Lorem" + br +
      "Lorem2" + br +
      "ipsum dolor sit ame#t#" + br +
      "Cras qiet.idunt."

  val sourceText_2 =
    "Lorem" + br +
      "Lorem2" + br +
      "ipsum dolor sit am#e#t" + br +
      "Cras qiet.idunt."

  test("Cursor move right one, line center") {
    viwTrue("l", sourceText_2, sourceText_1)
  }

  test("Cursor move right n, line start") {
    val actual =
      "Lorem" + br +
        "Lorem2" + br +
        "#i#psum dolor sit amet" + br +
        "Cras qiet.idunt."
    viwTrue("llllllllllllllllllllllllllllllll", actual, sourceText_1)
  }

  test("Cursor move right n, line end") {
    viwTrue("lllllllllllllll", sourceText_1, sourceText_1)
  }

  test("Cursor move right n, empty line ") {
    val actual =
      "Lorem" + br +
        "Lorem2" + br +
        "##" + br +
        "ipsum dolor sit amet "
    viwTrue("lllllllllllllllll", actual, actual)
  }

  // TODO: test trail space
}
