package viw.actions.cursor

import viw.actions.Action
import viw.internals.State

object StartOfLine extends Action {

  override def apply(state: State): State = {

    if (state.position.character == 0)
      state
    else new State(
      state.content,
      new State.Position(state.position.line, 0),
      state.selection,
      state.mode)
  }
}
