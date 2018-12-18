package viw.actions.mode

import viw.actions.Action
import viw.internals.State

object GoAction extends Action {

  override def apply(state: State): State = {
    new State(
      state.content,
      new State.Position(state.contentLines.size - 1, 0),
      state.selection,
      false)
  }
}
