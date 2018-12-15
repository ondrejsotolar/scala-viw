package viw

import viw.commands.Cursor
import viw.internals.State

object Viw {

  var viwMode: Boolean = false;

  def processKey(key: String, state: State): Option[State] = {
    if (!state.mode) {
      maybeTurnOn(key)
      return Some(state)
    } else
      process(key, state)
  }

  def process(key: String, state: State): Option[State] = key match {
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
    case _ => Some(state)
  }

  def maybeTurnOn(key: String): Unit = {
      viwMode = key == " "
  }
}
