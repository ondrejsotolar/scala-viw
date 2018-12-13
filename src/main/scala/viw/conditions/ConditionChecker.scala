package viw.conditions

import viw.internals.State

trait ConditionChecker {

  def all(conditions: List[Condition], state: State): Boolean = {
    conditions.forall(c => c.satisfy(state))
  }
}
