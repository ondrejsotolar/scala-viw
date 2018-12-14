package viw.actions.cursor

import viw.actions.Action
import viw.internals.State

object EndOfLine extends Action {

  override def apply(state: State): State = {

    val lineLength = state.contentLines(state.position.line).size - 1

    if (state.position.character == lineLength)
      state
    else new State(
      state.content,
      new State.Position(state.position.line, lineLength),
      state.selection,
      state.mode)
  }
}
