package viw.actions.mode

import viw.actions.Action
import viw.internals.State

object InsertAfterLineAction extends Action {

  override def apply(state: State): State = {

    val newCharPosition =
      if (state.contentLines(state.position.line).length > 0)
        state.contentLines(state.position.line).length
      else
        0

    new State(
      state.content,
      new State.Position(state.position.line, newCharPosition),
      state.selection,
      false)
  }
}
