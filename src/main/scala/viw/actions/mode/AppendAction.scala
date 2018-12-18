package viw.actions.mode

import viw.actions.Action
import viw.internals.State
import viw.internals.State.Position

object AppendAction extends Action {

  override def apply(state: State): State = {

    val newPosition =
      if (state.contentLines(state.position.line).length < 1)
        state.position
      else
        new Position(state.position.line, state.position.character + 1)

    new State(
      state.content,
      newPosition,
      state.selection,
      false
    )
  }
}
