package viw.actions.extra

import viw.StateUtils
import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State

object DeleteLeftAction extends Action {

  override def apply(state: State): State = {

    if (LinePosition.lineStart(state))
      return state

    if (LinePosition.lineEndExtended(state)) {
      return new State(
        state.content,
        new State.Position(state.position.line, state.position.character - 1),
        state.selection,
        state.mode)
    }

    val realPosition = StateUtils.getRealPosition(state)
    val newContent = state.content.take(realPosition - 1) ++ state.content.drop(realPosition)
    new State(
      newContent,
      new State.Position(state.position.line, state.position.character - 1),
      state.selection,
      state.mode)
  }

}
