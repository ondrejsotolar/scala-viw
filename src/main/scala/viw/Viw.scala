package viw

import viw.commands.{Cursor, Mode, Modify}
import viw.internals.State

object Viw {

  def textModifyKeys: List[String] = List("x","X","D","J")
  var prevKey: String = "";

  def processKey(key: String, state: State): Option[State] = {
    maybeRememberKey(key, state)

    key match {
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
      case "." => repeatLastCommand(state)
      case _ => Some(state)
    }
  }

  def maybeRememberKey(key: String, state: State): Unit = {
    if (state.mode && key != ".")
      prevKey = key
  }

  def repeatLastCommand(state: State): Option[State] = {
    if (textModifyKeys.contains(prevKey))
      processKey(prevKey, state)
    else
      Some(state)
  }
}
