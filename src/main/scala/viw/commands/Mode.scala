package viw.commands

import viw.actions.Action
import viw.actions.mode._
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

  object Append extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(AppendAction)
  }

  object OpenNewline extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(OpenNewlineAction)
  }

  object Substitute extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, LinePosition.isCursorOnCharacter)
    override def actions: List[Action] = List(SubstituteAction)
  }

  object GoToLastLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(GoAction)
  }

  object InsertInLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(InsertInLineAction)
  }

  object InsertAfterLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(InsertAfterLineAction)
  }

  object DeleteAndExit extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(DeleteLineAndExitAction)
  }
}
