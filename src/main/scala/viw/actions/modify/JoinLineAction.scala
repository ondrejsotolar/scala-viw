package viw.actions.modify

import viw.actions.Action
import viw.internals.State

object JoinLineAction extends Action {

  override def apply(state: State): State = {
    val nextLineStart = state.contentLines(state.position.line).length

    new State(
      state.content.take(nextLineStart) ++ " "
        ++ state.content.drop(nextLineStart + 1 /*sys.props("line.separator").length*/),
      new State.Position(state.position.line, nextLineStart),
      state.selection,
      state.mode)
  }
}
