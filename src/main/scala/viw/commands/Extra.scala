package viw.commands

import viw.actions.extra._
import viw.actions.Action
import viw.actions.cursor.CursorMoveUp
import viw.actions.modify.{BackspaceAction, DeleteAction}
import viw.conditions.{LinePosition, ViwMode}
import viw.internals.State

object Extra {
  object DeleteWholeLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(DeleteWholeLineAction)
  }

  object DeleteLeft extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.startOfFile))
    override def actions: List[Action] = List(DeleteLeftAction)
  }

  object DeleteRight extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, LinePosition.isCursorOnCharacter)
    override def actions: List[Action] = List(DeleteAction)
  }

  object DeleteUp extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.firstLine))
    override def actions: List[Action] = List(DeleteWholeLineAction, CursorMoveUp, DeleteWholeLineAction)
  }

  object DeleteDown extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.lastLine))
    override def actions: List[Action] = List(DeleteWholeLineAction, DeleteWholeLineAction)
  }
}
