package viw.actions.mode

import viw.actions.Action
import viw.internals.State

object InsertInLineAction extends Action {

  override def apply(state: State): State = {
    new State(
      state.content,
      new State.Position(state.position.line, 0),
      state.selection,
      false)
  }
}
