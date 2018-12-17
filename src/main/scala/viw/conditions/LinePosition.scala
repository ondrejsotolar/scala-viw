package viw.conditions
import viw.internals.State

object LinePosition {

  def isCursorOnCharacter(state: State): Boolean = {
    try {
      "" != state.contentLines(state.position.line)(state.position.character)
    } catch {
      case _: Throwable => false
    }

  }

  def lastLine(state: State): Boolean = {
    state.position.line == state.contentLines.size - 1
  }

  def lastLine(state: State, line: Int): Boolean = {
    line == state.contentLines.size - 1
  }

  def lineEnd(state: State): Boolean = {
    state.position.character == state.contentLines(state.position.line).size - 1
  }

  def lineEnd(state: State, line: Int, position: Int): Boolean = {
    position == state.contentLines(line).size - 1
  }

  def lineEndExtended(state: State): Boolean = {
    state.position.character == state.contentLines(state.position.line).size
  }

  def lineEndExtended(state: State, line: Int, position: Int): Boolean = {
    position == state.contentLines(line).size
  }

  def endOfFile(state: State): Boolean = {
    lastLine(state) && lineEnd(state)
  }

  def endOfFile(state: State, line: Int, char: Int): Boolean = {
    lastLine(state, line) && lineEnd(state, line, char)
  }

  def endOfFileExtended(state: State, line: Int, char: Int): Boolean = {
    lastLine(state, line) && lineEndExtended(state, line, char)
  }

  def startOfFile(state: State): Boolean = {
    firstLine(state) && lineStart(state)
  }

  def startOfFile(line: Int, char: Int): Boolean = {
    line == 0 && char == 0
  }


  def lineStart(state: State): Boolean = {
    state.position.character == 0
  }

  def lineStart(position: Int): Boolean = {
    position == 0
  }

  def firstLine(state: State): Boolean = {
    state.position.line == 0
  }
}
