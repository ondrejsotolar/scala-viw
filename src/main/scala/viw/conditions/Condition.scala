package viw.conditions

import viw.internals.State

trait Condition {
  def satisfy(state: State) : Boolean
}
