package viw.actions.mode

import viw.StateUtils
import viw.actions.Action
import viw.internals.State

object SubstituteAction extends Action {

  override def apply(state: State): State = {

    val realPosition = StateUtils.getRealPosition(state)

    new State(
      state.content.take(realPosition) ++ state.content.drop(realPosition + 1),
      state.position,
      state.selection,
      false)
  }
}