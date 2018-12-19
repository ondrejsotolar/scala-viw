package viw

import viw.commands.{Cursor, Mode, Modify}
import viw.internals.State

object Viw {

  def textModifyKeys: List[String] = List("x","X","D","J")
  var prevKey: String = "";

  def processKey(key: String, state: State): Option[State] = {
    maybeRememberKey(key, state)

    key match {
      /**/case " " => Mode.TurnViwOn.process(state)
      /**/case "i" => Mode.TurnViwOff.process(state)
      /**/case "a" => Mode.Append.process(state)
      /**/case "o" => Mode.OpenNewline.process(state)
      /**/case "s" => Mode.Substitute.process(state)
      /**/case "G" => Mode.GoToLastLine.process(state)
      /**/case "I" => Mode.InsertInLine.process(state)
      /**/case "A" => Mode.InsertAfterLine.process(state)
      /**/case "C" => Mode.DeleteAndExit.process(state)
      /*TESTED*/case "h" => Cursor.MoveLeft.process(state)
      /*TESTED*/case "l" => Cursor.MoveRight.process(state)
      /*TESTED*/case "j" => Cursor.MoveDown.process(state)
      /*TESTED*/case "k" => Cursor.MoveUp.process(state)
      /*TESTED*/case "0" => Cursor.MoveToStartOfLine.process(state)
      /*TESTED*/case "$" => Cursor.MoveToEndOfLine.process(state)
      /*TESTED*/case "w" => Cursor.MoveToStartOfNextWord.process(state)
      /*TESTED*/case "b" => Cursor.MoveToStartOfWord.process(state)
      /*TESTED*/case "e" => Cursor.MoveToEndOfWord.process(state)
      /*TESTED*/case "%" => Cursor.MatchBrackets.process(state)
      /*TESTED*/case "x" => Modify.Delete.process(state)
      /*TESTED*/case "X" => Modify.Backspace.process(state)
      /*TESTED*/case "D" => Modify.DeleteLine.process(state)
      /*____FIX*/case "J" => Modify.JoinLine.process(state)
      /*TESTED*/case "." => repeatLastCommand(state)
      /**/case _ => Some(state)
    }
  }

  def maybeRememberKey(key: String, state: State): Unit = {
    if (state.mode && key != "." && textModifyKeys.contains(key))
      prevKey = key
  }

  def repeatLastCommand(state: State): Option[State] = {
    if (textModifyKeys.contains(prevKey))
      processKey(prevKey, state)
    else
      Some(state)
  }
}
