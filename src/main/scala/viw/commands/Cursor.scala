package viw.commands

import viw.actions.Action
import viw.actions.cursor._
import viw.conditions.{LinePosition, ViwMode}
import viw.internals.State

object Cursor {

  object MoveUp extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.firstLine))
    override def actions : List[Action] = List(CursorMoveUp)
  }

  object MoveDown extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.lastLine))
    override def actions : List[Action] = List(CursorMoveDown)
  }

  object MoveLeft extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.lineStart))
    override def actions : List[Action] = List(CursorMoveLeft)
  }

  object MoveRight extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.lineEndExtended))
    override def actions : List[Action] = List(CursorMoveRight)
  }

  object MoveToEndOfLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions : List[Action] = List(EndOfLine)
  }

  object MoveToStartOfLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions : List[Action] = List(StartOfLine)
  }

  object MoveToStartOfNextWord extends CommandRunner{
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.endOfFile))
    override def actions: List[Action] = List(StartOfNextWord)
  }

  object MoveToStartOfWord extends CommandRunner{
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.startOfFile))
    override def actions: List[Action] = List(StartOfWord)
  }

  object MoveToEndOfWord extends CommandRunner{
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn, not(LinePosition.endOfFile))
    override def actions: List[Action] = List(EndOfWord)
  }

  object MatchBrackets extends CommandRunner{
    override def conditions: List[(State) => Boolean] = List(ViwMode.isViwOn)
    override def actions: List[Action] = List(MatchBracketsAction)
  }


}
