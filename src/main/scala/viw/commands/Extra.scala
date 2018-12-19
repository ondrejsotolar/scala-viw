package viw.commands

import viw.actions.extra.DeleteWholeLineAction
import viw.actions.{Action}
import viw.conditions.{ViwMode}
import viw.internals.State

object Extra {
  object DeleteWholeLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(DeleteWholeLineAction)
  }
}
