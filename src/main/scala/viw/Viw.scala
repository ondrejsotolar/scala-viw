package viw

import viw.internals.State

object Viw {

  var viwMode: Boolean = false;

  def processKey(key: String, state: State): Option[State] = {
    if (!state.mode && key != " ")
      Option(state)
    else
      viwMode = (key == " ")
    process(key, state)
  }

  def process(key: String, state: State): Option[State] = {
    if (key == "h")
      moveLeft_h(key, state)
    else if (key == "l")
      moveRight_l(key, state)
    else
      None
  }

  def moveLeft_h(key: String, state: State): Option[State] = {
    var newState = new State(
      state.content,
      new State.Position(state.position.line, state.position.character - 1),
      state.selection,
      state.mode);

    Option(newState)
  }

  def moveRight_l(key: String, state: State): Option[State] = {
    var newState = new State(
      state.content,
      new State.Position(state.position.line, state.position.character + 1),
      state.selection,
      state.mode);

    Option(newState)
  }
}
