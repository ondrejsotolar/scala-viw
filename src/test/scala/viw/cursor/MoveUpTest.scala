package viw.cursor

import org.scalatest.{BeforeAndAfter, FunSuite}
import viw.ViwTest

class MoveUpTest extends FunSuite with ViwTest with BeforeAndAfter {

  def br: String = sys.props("line.separator")

  val text1 =
    "Lorem" + br +
      "Lorem2" + br +
      "ipsum dolor sit ame#t#" + br +
      "Cras qiet.idunt."

  val text2 =
    "Lorem" + br +
      "Lorem2" + br +
      "ipsum dolor sit am#e#t" + br +
      "Cras qiet.idunt."

  val text3 =
    "Lore#m#" + br +
      "Lorem2" + br +
      "ipsum dolor sit amet" + br +
      "Cras qiet.idunt."

  val text4 =
    "Lo#r#em" + br +
      "Lorem2" + br +
      "ipsum dolor sit amet" + br +
      "Cras qiet.idunt."

  val text5 =
    "Lorem" + br +
      "Lo#r#em2" + br +
      "ipsum dolor sit amet" + br +
      "Cras qiet.idunt."

  val text6 =
    "Lorem" + br +
      "Lorem#2#" + br +
      "ipsum dolor sit amet" + br +
      "Cras qiet.idunt."

  val text7 =
    "Lore#m#" + br +
      "Lorem2" + br +
      "ipsum dolor sit amet" + br +
      "Cras qiet.idunt."

  test("Cursor move up one, first line end") {
    viwTrue("k", text3, text3)
  }

  test("Cursor move up n, first line end") {
    viwTrue("kkkkkkkkkkkkkkkkkkkk", text3, text3)
  }

  test("Cursor move up one, center line center pos") {
    viwTrue("k", text5, text4)
  }

  test("Cursor move up one, end of line") {
    viwTrue("k", text1, text6)
  }

  test("Cursor move up n, end of line") {
    viwTrue("kkkkkkkkkkkkkkkk", text1, text7)
  }
}