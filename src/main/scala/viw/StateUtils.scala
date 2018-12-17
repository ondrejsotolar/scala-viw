package viw

import viw.internals.State

object StateUtils {

  def getRealPosition(state: State): Int = {
    def getRealPosition(state: State, line: Int, fromStart: Int): Int = {
      if (line == state.position.line)
        fromStart + state.position.character
      else
        getRealPosition(state, line + 1, fromStart + state.contentLines(line).length)
    }
    getRealPosition(state, 0, 0)
  }

}
