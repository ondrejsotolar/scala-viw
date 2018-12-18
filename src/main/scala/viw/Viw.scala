package viw

import viw.commands.{Cursor, Mode, Modify}
import viw.internals.State

object Viw {

  def processKey(key: String, state: State): Option[State] = key match {
    case " " => Mode.TurnViwOn.process(state)
    case "i" => Mode.TurnViwOff.process(state)
    case "a" => Mode.Append.process(state)
    case "o" => Mode.OpenNewline.process(state)
    case "s" => Mode.Substitute.process(state)
    case "G" => Mode.GoToLastLine.process(state)
    case "I" => Mode.InsertInLine.process(state)
    case "A" => Mode.InsertAfterLine.process(state)
    case "C" => Mode.DeleteAndExit.process(state)
    case "h" => Cursor.MoveLeft.process(state)
    case "l" => Cursor.MoveRight.process(state)
    case "j" => Cursor.MoveDown.process(state)
    case "k" => Cursor.MoveUp.process(state)
    case "0" => Cursor.MoveToStartOfLine.process(state)
    case "$" => Cursor.MoveToEndOfLine.process(state)
    case "w" => Cursor.MoveToStartOfNextWord.process(state)
    case "b" => Cursor.MoveToStartOfWord.process(state)
    case "e" => Cursor.MoveToEndOfWord.process(state)
    case "%" => Cursor.MatchBrackets.process(state)
    case "x" => Modify.Delete.process(state)
    case "X" => Modify.Backspace.process(state)
    case "D" => Modify.DeleteLine.process(state)
    case "J" => Modify.JoinLine.process(state)
    case _ => Some(state)
  }
}
