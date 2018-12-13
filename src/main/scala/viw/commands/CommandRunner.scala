package viw.commands

import viw.actions.{Action, ActionProcessor}
import viw.conditions.{Condition, ConditionChecker}
import viw.internals.State

trait CommandRunner extends ConditionChecker with ActionProcessor {
  def process(conditions: List[Condition], actions: List[Action], state: State): Option[State] = {
    if (all(conditions, state))
      Some(applyAll(actions, state))
    else
      Some(state)

  }
}
