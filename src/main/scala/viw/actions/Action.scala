package viw.actions

import viw.internals.State

trait Action {
  def apply(state: State) : State
}
