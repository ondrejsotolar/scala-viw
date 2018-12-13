package viw.actions

import viw.internals.State

trait ActionProcessor {

  def applyAll(actions: List[Action], state: State): State = actions match {
    case Nil => state
    case x :: tail => applyAll(tail, x.apply(state))
  }
}
