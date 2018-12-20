package viw

import viw.commands._
import viw.internals.State

object Viw {

  def repeatableCommands: List[String] = List("x","X","D","J","dd","dh","dl","dj","dk")
  def combinedFirstKeys: List[String] = List("d")
  def combinedSecondKeys: List[String] = List("d","h","l","j","k")

  var repeatableCommand = ""
  var prevKey = ""

  def processKey(key: String, state: State): Option[State] = {

    val combinedCommand: String = combineWithPrevCommand(key, state)

    combinedCommand match {
      case " " => applyCommand(Mode.TurnViwOn, state)
      case "i" => applyCommand(Mode.TurnViwOff, state)
      case "a" => applyCommand(Mode.Append, state)
      case "o" => applyCommand(Mode.OpenNewline, state)
      case "s" => applyCommand(Mode.Substitute, state)
      case "G" => applyCommand(Mode.GoToLastLine, state)
      case "I" => applyCommand(Mode.InsertInLine, state)
      case "A" => applyCommand(Mode.InsertAfterLine, state)
      case "C" => applyCommand(Mode.DeleteAndExit, state)
      case "h" => applyCommand(Cursor.MoveLeft, state)
      case "l" => applyCommand(Cursor.MoveRight, state)
      case "j" => applyCommand(Cursor.MoveDown, state)
      case "k" => applyCommand(Cursor.MoveUp, state)
      case "0" => applyCommand(Cursor.MoveToStartOfLine, state)
      case "$" => applyCommand(Cursor.MoveToEndOfLine, state)
      case "w" => applyCommand(Cursor.MoveToStartOfNextWord, state)
      case "b" => applyCommand(Cursor.MoveToStartOfWord, state)
      case "e" => applyCommand(Cursor.MoveToEndOfWord, state)
      case "%" => applyCommand(Cursor.MatchBrackets, state)
      case "x" => applyCommand(Modify.Delete, state)
      case "X" => applyCommand(Modify.Backspace, state)
      case "D" => applyCommand(Modify.DeleteLine, state)
      case "J" => applyCommand(Modify.JoinLine, state)
      case "." => repeatLastCommand(state)
      /*UT*/case "dd" => applyCommand(Extra.DeleteWholeLine, state)
      /*UT*/case "dh" => applyCommand(Extra.DeleteLeft, state)
      /*UT*/case "dl" => applyCommand(Extra.DeleteRight, state)
      /*UT*/case "dk" => applyCommand(Extra.DeleteUp, state)
      /*UT*/case "dj" => applyCommand(Extra.DeleteDown, state)
      case _ => Some(state)
    }
  }

  def applyCommand(runner: CommandRunner, state: State) : Option[State] = {
    prevKey = ""
    runner.process(state)
  }

  def combineWithPrevCommand(key: String, state: State): String = {
    if (!state.mode)
      return "NONE"

    if (prevKey == "" && combinedFirstKeys.contains(key)) {
      prevKey = key
      return "NONE"
    }

    if (combinedSecondKeys.contains(key)) {
      rememberRepeatable(prevKey + key)
      return prevKey + key
    }

    rememberRepeatable(key)
    key
  }

  def rememberRepeatable(command: String): Unit = {
    if (command != "." && repeatableCommands.contains(command))
      repeatableCommand = command
  }

  def repeatLastCommand(state: State): Option[State] = {
    if (repeatableCommands.contains(repeatableCommand))
      processKey(repeatableCommand, state)
    else
      Some(state)
  }
}
