package viw.conditions

import viw.internals.State

object ViwMode {

  def isViwOn(state: State): Boolean = {
    state.mode
  }
}
