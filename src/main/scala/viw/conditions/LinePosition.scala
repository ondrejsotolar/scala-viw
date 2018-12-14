package viw.conditions
import viw.internals.State

object LinePosition {

  def lastLine(state: State): Boolean = {
    state.position.line == state.contentLines.size - 1
  }

  def lineEnd(state: State): Boolean = {
    state.position.character == state.contentLines(state.position.line).size - 1
  }

  def endOfFile(state: State): Boolean = {
    lastLine(state) && lineEnd(state)
  }

  def extendedLineEnd(state: State): Boolean = {
    state.position.character == state.contentLines(state.position.line).size
  }

  def lineStart(state: State): Boolean = {
    state.position.character == 0
  }

  def firstLine(state: State): Boolean = {
    state.position.line == 0
  }
}
