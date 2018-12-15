package viw.actions.cursor

import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State

object StartOfWord extends Action {

  override def apply(state: State): State = {

    val nextWordPosition: (Int,Int) = prevWordStart(state, state.position.line, state.position.character, false)

    new State(
      state.content,
      new State.Position(nextWordPosition._1, nextWordPosition._2),
      state.selection,
      state.mode)
  }

  def prevWordStart(state: State, line: Int, char: Int, prevNonWhitespace: Boolean): (Int,Int) = {
    if (LinePosition.startOfFile(line, char))
      return (0,0)

    if (LinePosition.lineStart(char))
      return prevWordStart(state, line - 1, state.contentLines(line - 1).size - 1, false)

    val current: Char = state.contentLines(line)(char)
    if (current.isWhitespace && prevNonWhitespace)
      (line, char + 1)
    else
      prevWordStart(state, line, char - 1, current.isWhitespace)
  }
}