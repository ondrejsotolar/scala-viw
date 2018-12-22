package viw.mandatory
import org.scalatest.FunSuite
import viw.{ViwTest}

class MatchingBracketTest extends FunSuite with ViwTest {
  test("Cursor to match backet - {") {
    viwTrue(
      "%",
      """Lorem #{#ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit a}met""".stripMargin,
      """Lorem {ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit a#}#met""".stripMargin,
    )
  }

  test("Cursor to match backet - (") {
    viwTrue(
      "%",
      """Lorem #(#ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit a)met""".stripMargin,
      """Lorem (ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit a#)#met""".stripMargin,
    )
  }
  test("Cursor to match backet in reverse - )") {
    viwTrue(
      "%",
      """Lorem (ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit #)#amet""".stripMargin,
      """Lorem #(#ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit )amet""".stripMargin,
    )
  }

  test("Cursor to match backet in reverse without starting ") {
    viwTrue(
      "%",
      """Lorem ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit a#)#met""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit a#)#met""".stripMargin,
    )
  }

  test("Cursor to match backet - ed before start - no change") {
    viwTrue(
      "%",
      """Lorem #(#ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
      """Lorem #(#ipsum dolor sit.amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
    )
  }

  test("Cursor to match backet - other types") {
    viwTrue(
      "%",
      """Lorem #(#ipsum do}]lor sit.amet, c  > yes
        |  !.!  dolor sit a)met""".stripMargin,
      """Lorem (ipsum do}]lor sit.amet, c  > yes
        |  !.!  dolor sit a#)#met""".stripMargin,
    )
  }

  test("Cursor to match backet - nesting") {
    viwTrue(
      "%",
      """Lorem #(#ipsu(()){}[]m do}]lor sit.amet, c  > yes
        |  !.!  dolor sit a)met""".stripMargin,
      """Lorem (ipsu(()){}[]m do}]lor sit.amet, c  > yes
        |  !.!  dolor sit a#)#met""".stripMargin,
    )
  }

  test("Cursor to match backet - non-bracket - behind") {
    viwTrue(
      "%",
      """Lorem (i#p#su(()){}[]m do}]lor sit.amet, c  > yes
        |  !.!  dolor sit a)met""".stripMargin,
      """Lorem #(#ipsu(()){}[]m do}]lor sit.amet, c  > yes
        |  !.!  dolor sit a)met""".stripMargin,
    )
  }

  test("Cursor to match backet - non-bracket - before") {
    viwTrue(
      "%",
      """L#o#rem (ipsu(()){}[]m do}]lor sit.amet, c  > yes
        |  !.!  dolor sit a)met""".stripMargin,
      """Lorem #(#ipsu(()){}[]m do}]lor sit.amet, c  > yes
        |  !.!  dolor sit a)met""".stripMargin,
    )
  }
}
