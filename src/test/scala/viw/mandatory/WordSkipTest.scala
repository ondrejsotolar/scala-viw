package viw.mandatory
import org.scalatest.FunSuite
import viw.{ViwTest}

class WordSkipTest extends FunSuite with ViwTest {

  test("Move word back - same word") {
    viwTrue(
      "b",
      """Lorem ipsum dolor sitam#e#t, c  > yes""".stripMargin,
      """Lorem ipsum dolor #s#itamet, c  > yes""".stripMargin,
    )
  }

  test("Move word back - one word away") {
    viwTrue(
      "b",
      """Lorem ipsum #d#olor sit.amet, c  > yes""".stripMargin,
      """Lorem #i#psum dolor sit.amet, c  > yes""".stripMargin,
    )
  }

  test("Move word back - nonletters") {
    viwTrue(
      "b",
      """Lorem ipsum dolor sit.#a#met, c  > yes""".stripMargin,
      """Lorem ipsum dolor #s#it.amet, c  > yes""".stripMargin,
    )
  }

  test("Move word back - newline") {
    viwTrue(
      "b",
      """Lorem ipsum dolor sit.amet, c  > yes
        |#d#olor sit amet""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > #y#es
        |dolor sit amet""".stripMargin,
    )
  }

  test("Move word back - newline with nonletter") {
    viwTrue(
      "b",
      """Lorem ipsum dolor sit.amet, c  > yes
        |  !.!  #d#olor sit amet""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > #y#es
        |  !.!  dolor sit amet""".stripMargin,

    )
  }

  test("Move word back - on space") {
    viwTrue(
      "b",
      """Lorem ipsum# #dolor sit.amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
      """Lorem #i#psum dolor sit.amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
    )
  }

  test("Move word back - nonletter") {
    viwTrue(
      "b",
      """Lorem ipsum dolor sit#.#amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
      """Lorem ipsum dolor #s#it.amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
    )
  }

  test("Move word back - non letters") {
    viwTrue(
      "b",
      """Lorem ipsum dolor sit.amet, c  > yes
        |  !.!  #d#olor sit amet""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > #y#es
        |  !.!  dolor sit amet""".stripMargin,
    )
  }

  test("Move word back - sof") {
    viwTrue(
      "b",
      """Loremips#u#m dolor""",
      """#L#oremipsum dolor"""
    )
  }

  test("No prev word") {
    viwTrue(
      "b",
      """#L#orem ipsum """,
      """#L#orem ipsum """
    )
  }

  // next word
  test("Move word forward - start of next word") {
    viwTrue(
      "w",
      """Lorem ipsum do#l#or sitamet, c  > yes""".stripMargin,
      """Lorem ipsum dolor #s#itamet, c  > yes""".stripMargin,
    )
  }

  test("Move word forward - start of word") {
    viwTrue(
      "w",
      """Lorem ipsum #d#olor sit.amet, c  > yes""".stripMargin,
      """Lorem ipsum dolor #s#it.amet, c  > yes""".stripMargin,
    )
  }

  test("Move word forward to previous word - separated by non-letter") {
    viwTrue(
      "w",
      """Lorem ipsum dol#o#r.sit.amet, c  > yes""".stripMargin,
      """Lorem ipsum dolor.#s#it.amet, c  > yes""".stripMargin,
    )
  }

  test("Move word forward - newline") {
    viwTrue(
      "w",
      """Lorem ipsum dolor sit.amet, c  > #y#es
        |dolor sit amet""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > yes
        |#d#olor sit amet""".stripMargin,
    )
  }

  test("Move word forward - newline + nonletters") {
    viwTrue(
      "w",
      """Lorem ipsum dolor sit.amet, c  > #y#es
        |  !.!  dolor sit amet""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > yes
        |  !.!  #d#olor sit amet""".stripMargin,
    )
  }

  test("Move word forward - starting on a blank space") {
    viwTrue(
      "w",
      """Lorem ipsum# #dolor sit.amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
      """Lorem ipsum #d#olor sit.amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
    )
  }

  test("Move word forward - starting on a nonletter") {
    viwTrue(
      "w",
      """Lorem ipsum dolor sit#.#amet, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
      """Lorem ipsum dolor sit.#a#met, c  > yes
        |  !.!  dolor sit amet""".stripMargin,
    )
  }

  test("Move word forward - more nonletters and newline") {
    viwTrue(
      "w",
      """Lorem ipsum dolor sit.amet, c  > #y#es
        |  !.!  dolor sit amet""".stripMargin,
      """Lorem ipsum dolor sit.amet, c  > yes
        |  !.!  #d#olor sit amet""".stripMargin,
    )
  }

  test("Move word forward - last word") {
    viwTrue(
      "w",
      """Lorem ipsum do#l#or""",
      """Lorem ipsum do#l#or"""
    )
  }

  test("Move word forward - no previous word") {
    viwTrue(
      "w",
      """Lorem #i#psum """,
      """Lorem #i#psum """
    )
  }

  test("End of word") {
    viwTrue(
      "e",
      """Lorem #i#psum dolor sit amet, consectetur adipiscing elit.""",
      """Lorem ipsu#m# dolor sit amet, consectetur adipiscing elit."""
    )
  }

  test("End of word - cursor is already on the last char of a word"){
    viwTrue(
      "e",
      """Lorem ipsu#m# dolor""",
      """Lorem ipsum dolo#r#"""
    )
  }

  test("End of word - cursor starts on non-letter and word ends before non-letter") {
    viwTrue(
      "e",
      """Lorem #.# ipsum.dolor sit amet, consectetur adipiscing elit.""",
      """Lorem . ipsu#m#.dolor sit amet, consectetur adipiscing elit."""
    )
  }

  test("End of word - separated by new line") {
    viwTrue(
      "e",
      """Lorem ipsum.dolor sit amet, consectetur adipiscing elit# #
        |secondTestword with some extra fluff""".stripMargin,
      "Lorem ipsum.dolor sit amet, consectetur adipiscing elit " + System.lineSeparator() +
        "secondTestwor#d# with some extra fluff"
    )
  }

  test("End of word - ends on last character of file") {
    viwTrue(
      "e",
      """Lorem ipsum d#o#lor""",
      """Lorem ipsum dolo#r#"""
    )
  }

  test("End of word - ends on last character of line with only non-letters on next line") {
    viwTrue(
      "e",
      """Lorem ipsum.dolor sit amet, consectetur adipiscing e#l#it
        | ?.""".stripMargin,
      """Lorem ipsum.dolor sit amet, consectetur adipiscing eli#t#
        | ?.""".stripMargin
    )
  }

  test("There is no next word 222") {
    viwTrue(
      "e",
      """Lorem ipsum dolor # #? """,
      """Lorem ipsum dolor # #? """
    )
  }

  test("There is no next word 22") {
    viwTrue(
      "e",
      """Lorem ipsum dolor # # """,
      """Lorem ipsum dolor # # """
    )
  }
}
