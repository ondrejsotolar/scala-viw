package viw.actions.cursor

import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State

object MatchBracketsAction extends Action {

  def brackets: List[Char] = List('(',')','[',']','{','}')

  override def apply(state: State): State = {
    val newPosition =
      if (surroundingsContainBracket(state))
        findMatchingBracket(state)
      else
        getFirstBracketOnLine(state)

    if (newPosition._1 < 0)
      return state
    else new State(
      state.content,
      new State.Position(newPosition._1, newPosition._2),
      state.selection,
      state.mode)
  }

  def getFirstBracketOnLine(state: State) : (Int,Int) = {
    (0,0)
  }

  def findMatchingBracket(state: State): (Int,Int) = {
    (0,0)
  }

  def surroundingsContainBracket(state: State): Boolean = {
    if (LinePosition.lineStart(state))
      brackets.exists(x => x == state.contentLines(state.position.line)(0))
    if (LinePosition.extendedLineEnd(state))
      brackets.exists(x => x == state.contentLines(state.position.line)(state.position.character - 1))
    else
      List(state.contentLines(state.position.line)(state.position.character - 1),
       state.contentLines(state.position.line)(state.position.character))
        .exists(y => brackets.exists(x => x == y))
  }
}