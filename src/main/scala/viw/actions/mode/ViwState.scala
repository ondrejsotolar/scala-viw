package viw.actions.mode

import viw.actions.Action
import viw.internals.State

object ViwState {
  object TurnViwOn extends Action {
    override def apply(state: State): State = {
      new State(
        state.content,
        state.position,
        state.selection,
        true
      )
    }
  }
  object TurnViwOff extends Action {
    override def apply(state: State): State = {
      new State(
        state.content,
        state.position,
        state.selection,
        false
      )
    }
  }
}
