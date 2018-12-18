package viw

import viw.internals.State

object StateUtils {

  // TODO: try with newlines \n
  def getRealPosition(state: State): Int = {
    def getRealPosition(state: State, line: Int, fromStart: Int): Int = {
      if (line == state.position.line)
        fromStart + state.position.character
      else
        getRealPosition(state, line + 1, fromStart + state.contentLines(line).length)
    }
    getRealPosition(state, 0, 0)
  }

  def getNextLineRealPosition(state: State): Int = {
    def nextLine(state: State, line: Int, fromStart: Int): Int = {
      if (line == state.position.line + 1)
        fromStart
      else
        nextLine(state, line + 1, fromStart + state.contentLines(line).length + "\n".length)
    }
    nextLine(state, 0, 0)
  }
}
