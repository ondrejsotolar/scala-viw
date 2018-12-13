package viw.commands

import viw.actions.{Action, CursorMoveLeft}
import viw.conditions.{Condition, LineStart, Not}
import viw.internals.State

object MoveLeft extends CommandRunner {

  def conditions: List[Condition] = List(new Not(LineStart))
  def actions : List[Action] = List(CursorMoveLeft)

  def process(state: State): Option[State] =
    super.process(conditions, actions, state)

}
