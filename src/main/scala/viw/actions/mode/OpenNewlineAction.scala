package viw.actions.mode

import viw.StateUtils
import viw.actions.Action
import viw.internals.State
import viw.internals.State.Position

object OpenNewlineAction extends Action {

  override def apply(state: State): State = {

    val endOfLine = StateUtils.getNextLineRealPosition(state)

    new State(
      state.content.take(endOfLine) ++ "\n" ++ state.content.drop(endOfLine),
      new Position(state.position.line + 1, 0),
      state.selection,
      false
    )
  }
}