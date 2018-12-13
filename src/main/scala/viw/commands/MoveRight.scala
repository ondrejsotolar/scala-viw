package viw.commands

import viw.actions.{Action, CursorMoveRight}
import viw.conditions.{Condition, LineEnd, Not}
import viw.internals.State

object MoveRight extends CommandRunner {

  def conditions: List[Condition] = List(new Not(LineEnd))
  def actions : List[Action] = List(CursorMoveRight)

  def process(state: State): Option[State] =
    super.process(conditions, actions, state)

}
