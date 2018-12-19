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

    if (LinePosition.endOfFile(state, line, char)) {
      if (!state.contentLines(line)(char).isLetterOrDigit)
        return (-1,-1)
      else
        return (line, char)
    }

    val current: Char = state.contentLines(line)(char)
    if (LinePosition.lineEnd(state, line, char)) {
      val result = wordEnd(state, line + 1, 0, true)
      if (result._1 < 0 && current.isLetterOrDigit)
        return (line, char)
      else
        return result
    }

    val next: Char = state.contentLines(line)(char + 1)
    if (!next.isLetterOrDigit && current.isLetterOrDigit && prevNonWhitespace)
      (line, char)
    else
      wordEnd(state, line, char + 1, next.isLetterOrDigit)
  }
}