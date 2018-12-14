package viw.commands

import viw.actions.Action
import viw.actions.cursor._
import viw.conditions.LinePosition
import viw.internals.State

object Cursor {

  object MoveUp extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(not(LinePosition.firstLine))
    override def actions : List[Action] = List(CursorMoveUp)
  }

  object MoveDown extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(not(LinePosition.lastLine))
    override def actions : List[Action] = List(CursorMoveDown)
  }

  object MoveLeft extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(not(LinePosition.lineStart))
    override def actions : List[Action] = List(CursorMoveLeft)
  }

  object MoveRight extends CommandRunner {
    override def conditions: List[(State) => Boolean] = List(not(LinePosition.extendedLineEnd))
    override def actions : List[Action] = List(CursorMoveRight)
  }

  object MoveToEndOfLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = always
    override def actions : List[Action] = List(EndOfLine)
  }

  object MoveToStartOfLine extends CommandRunner {
    override def conditions: List[(State) => Boolean] = always
    override def actions : List[Action] = List(StartOfLine)
  }

  object MoveToStartOfNextWord extends CommandRunner{
    override def conditions: List[(State) => Boolean] = List(not(LinePosition.endOfFile))
    override def actions: List[Action] = List()
  }
}
