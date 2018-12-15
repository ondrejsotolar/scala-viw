package viw.actions.cursor

import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State

object EndOfWord extends Action {

  override def apply(state: State): State = {

    val nextWordPosition: (Int,Int) = wordEnd(state, state.position.line, state.position.character, false)
    if (nextWordPosition._1 < 0)
      return state

    new State(
      state.content,
      new State.Position(nextWordPosition._1, nextWordPosition._2),
      state.selection,
      state.mode)
  }

  def wordEnd(state: State, line: Int, char: Int, prevNonWhitespace: Boolean): (Int,Int) = {

    if (LinePosition.endOfFile(state, line, char))
      return (line, char)

    if (LinePosition.lineEnd(state, line, char))
      return wordEnd(state, line + 1, 0, false)

    val current: Char = state.contentLines(line)(char)
    if (current.isWhitespace && prevNonWhitespace)
      (line, char - 1)
    else
      wordEnd(state, line, char + 1, !current.isWhitespace)
  }
}