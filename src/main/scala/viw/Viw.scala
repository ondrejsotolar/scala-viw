package viw

import viw.commands.{MoveLeft, MoveRight}
import viw.internals.State

object Viw {

  var viwMode: Boolean = false;

  def processKey(key: String, state: State): Option[State] = {
    if (!state.mode) {
      if (key == " ") {
        viwMode = (key == " ")
      }
      return Some(state)
    } else
      process(key, state)
  }

  def process(key: String, state: State): Option[State] = key match {
    case "h" => MoveLeft.process(state)
    case "l" => MoveRight.process(state)
    case _ => Some(state)
  }


}
