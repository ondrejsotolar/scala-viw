package viw.actions.modify

import viw.StateUtils
import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State
import viw.internals.State.Position

object DeleteAction extends Action {

  override def apply(state: State): State = {
    val realPosition = StateUtils.getRealPosition(state)
    val newPosition =
      if (LinePosition.lineEnd(state) && !LinePosition.lineStart(state))
          state.position.character - 1
      else
          state.position.character


    new State(
      state.content.take(realPosition) ++ state.content.drop(realPosition + 1),
      new Position(state.position.line, newPosition),
      state.selection,
      state.mode)
  }
}
