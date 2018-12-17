package viw.actions.modify

import viw.{StateUtils, internals}
import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State

object BackspaceAction extends Action {

  override def apply(state: State): State = {

    if (LinePosition.lineStart(state))
      return state

    val realPosition = StateUtils.getRealPosition(state)
    val newContent = state.content.take(realPosition) ++ state.content.drop(realPosition + 1)

    new State(
      newContent,
      new State.Position(state.position.line, state.position.character - 1),
      state.selection,
      state.mode)
  }

}
