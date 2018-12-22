package viw.mandatory
import org.scalatest.FunSuite
import viw.{ViwTest}

class Repeat extends FunSuite with ViwTest {
  test("Repeat - join ln, delete foward, repeat two times") {
    viwTrue(
      "Jx..",
      """Lorem #i#psum dolor
        |sit amet
        |consequitor sth""".stripMargin,
      """Lorem ipsum dolor#t# amet
        |consequitor sth""".stripMargin,
    )
  }

  test("Repeat - delete, delete backwards and repeat") {
    viwTrue(
      "xX.",
      """Lorem #i#psum dolor
        |sit amet
        |consequitor sth""".stripMargin,
      """Lore#m#sum dolor
        |sit amet
        |consequitor sth""".stripMargin,
    )
  }
}
