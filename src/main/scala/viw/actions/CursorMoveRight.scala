package viw.actions

import viw.internals.State

object CursorMoveRight extends Action {

  def apply(state: State): State = {

    val newState = new State(
      state.content,
      new State.Position(state.position.line, state.position.character + 1),
      state.selection,
      state.mode);

    newState
  }
}