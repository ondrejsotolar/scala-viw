package viw.commands

import viw.actions.Action
import viw.actions.mode.ViwState
import viw.actions.modify.JoinLineAction
import viw.commands.Modify.JoinLine.not
import viw.conditions.{LinePosition, ViwMode}
import viw.internals.State

object Mode {

  object TurnViwOn extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(not(ViwMode.isViwOn))
    override def actions: List[Action] = List(ViwState.TurnViwOn)
  }

  object TurnViwOff extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(ViwState.TurnViwOff)
  }
}
