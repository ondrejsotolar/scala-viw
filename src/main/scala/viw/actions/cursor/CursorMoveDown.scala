package viw.actions.cursor

import viw.actions.Action
import viw.internals.State

object CursorMoveDown extends Action {

  def apply(state: State): State = {

    val nextLinePosition =
      if (state.contentLines(state.position.line + 1).size > state.position.character)
        state.position.character
      else
        getPositionOnLineEnd(state)

    val newState = new State(
      state.content,
      new State.Position(state.position.line + 1, nextLinePosition),
      state.selection,
      state.mode);

    newState
  }

  def getPositionOnLineEnd(state: State) : Int = {
    val lineEndDistance = state.contentLines(state.position.line).size - state.position.character

    val difference = state.contentLines(state.position.line + 1).size - lineEndDistance

    if (difference < 0)
        0 else difference
  }
}
