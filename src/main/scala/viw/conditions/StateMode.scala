package viw.conditions

import viw.internals.State

object StateMode {

  def isViwOn(state: State): Boolean = {
    state.mode
  }
}
