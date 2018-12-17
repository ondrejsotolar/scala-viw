package viw.actions.modify

import viw.{StateUtils, internals}
import viw.actions.Action
import viw.internals.State

object DeleteLineAction extends Action {

  override def apply(state: State): State = {
    val realPosition = StateUtils.getRealPosition(state)
    val nextLineStart = state.contentLines(state.position.line).length - state.position.character

    new State(
      state.content.take(realPosition) ++ state.content.drop(realPosition + nextLineStart),
      new State.Position(state.position.line, state.position.character - 1),
      state.selection,
      state.mode)
  }
}
