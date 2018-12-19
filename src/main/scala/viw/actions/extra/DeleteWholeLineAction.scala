package viw.actions.extra

import viw.StateUtils
import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State
import viw.internals.State.Position

object DeleteWholeLineAction extends Action
{
  override def apply(state: State): State = {

    val newLineIndex =
      if (LinePosition.lastLine(state) && !LinePosition.firstLine(state))
        state.position.line - 1
      else
        state.position.line

    val newState =
      if (LinePosition.lastLine(state) && !LinePosition.firstLine(state))
        state.content.take(StateUtils.getLineStartRealPosition(state) -1)
      else
        state.content.take(StateUtils.getLineStartRealPosition(state)) ++
        state.content.drop(StateUtils.getNextLineRealPosition(state))

    new State(
      newState,
      new Position(newLineIndex, 0),
      state.selection,
      state.mode)
  }
}
