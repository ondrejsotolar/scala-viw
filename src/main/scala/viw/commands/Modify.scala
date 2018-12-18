package viw.commands

import viw.actions.Action
import viw.actions.modify.{BackspaceAction, DeleteAction, DeleteLineAction, JoinLineAction}
import viw.conditions.{LinePosition, ViwMode}
import viw.internals.State

object Modify {

  object Delete extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, LinePosition.isCursorOnCharacter)
    override def actions: List[Action] = List(DeleteAction)
  }

  object Backspace extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.startOfFile))
    override def actions: List[Action] = List(BackspaceAction)
  }

  object DeleteLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.lineEndExtended))
    override def actions: List[Action] = List(DeleteLineAction)
  }

  object JoinLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.lastLine))
    override def actions: List[Action] = List(JoinLineAction)
  }
}
