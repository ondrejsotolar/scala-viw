package viw.actions.mode

import viw.StateUtils
import viw.actions.Action
import viw.internals.State

object DeleteLineAndExitAction extends Action {

  override def apply(state: State): State = {

    if (state.contentLines(state.position.line).length == 0)
      return new State(
        state.content,
        state.position,
        state.selection,
        false)

    val realPosition = StateUtils.getRealPosition(state)
    val nextLineStart = state.contentLines(state.position.line).length - state.position.character

    new State(
      state.content.take(realPosition) ++ state.content.drop(realPosition + nextLineStart),
      new State.Position(state.position.line, state.position.character),
      state.selection,
      false)
  }
}