package viw.commands

import viw.actions.Action
import viw.internals.State

abstract class CommandRunner {
  def conditions: List[(State) => Boolean]
  def actions : List[Action]

  def process(state: State): Option[State] = {
    if (conditions.forall(c => c(state)))
      Some(applyAll(actions, state))
    else
      Some(state)
  }

  def applyAll(actions: List[Action], state: State): State = actions match {
    case Nil => state
    case x :: tail => applyAll(tail, x.apply(state))
  }

  def not(condition: (State) => Boolean): (State) => Boolean = !condition(_)

  def always: List[(State) => Boolean] = List((_) => true)
}
