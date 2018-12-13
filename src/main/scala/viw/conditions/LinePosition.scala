package viw.conditions
import viw.internals.State

object LineStart extends Condition {
  override def satisfy(state: State): Boolean = {
    state.position.character == 0
  }
}

object LineEnd extends Condition {
  override def satisfy(state: State): Boolean = {
    state.position.character == state.contentLines(state.position.line).size - 1
  }
}

