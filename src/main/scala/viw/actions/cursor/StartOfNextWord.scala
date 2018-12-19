package viw.actions.cursor

import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State

object StartOfNextWord extends Action {

  override def apply(state: State): State = {

    val nextWordPosition: (Int,Int) = nextWordStart(state, state.position.line, state.position.character, false)
    if (nextWordPosition._1 < 0)
      return state

    new State(
      state.content,
      new State.Position(nextWordPosition._1, nextWordPosition._2),
      state.selection,
      state.mode)
  }

  def nextWordStart(state: State, line: Int, char: Int, prevWhitespace: Boolean): (Int,Int) = {

    val current: Char = state.contentLines(line)(char)

    if ((LinePosition.lineStart(char) && current.isLetterOrDigit)
      || (prevWhitespace && current.isLetterOrDigit))
      (line, char)
    else
      if (LinePosition.lineEnd(state, line, char))
        if (LinePosition.lastLine(state, line))
          (-1,-1)
         else
          nextWordStart(state, line + 1, 0, !current.isLetterOrDigit)
      else
        nextWordStart(state, line, char + 1, !current.isLetterOrDigit)
  }
}