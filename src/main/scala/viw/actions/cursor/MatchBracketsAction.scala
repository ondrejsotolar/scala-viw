package viw.actions.cursor

import viw.actions.Action
import viw.conditions.LinePosition
import viw.internals.State

object MatchBracketsAction extends Action {

  def brackets: List[Char] = List('(',')','[',']','{','}')

  override def apply(state: State): State = {
    val newPosition =
      if (surroundingsContainBracket(state))
        findMatchingBracket(state)
      else
        getFirstBracketOnLine(state)

    if (newPosition._1 < 0 || newPosition._2 < 0)
      state
    else new State(
      state.content,
      new State.Position(newPosition._1, newPosition._2),
      state.selection,
      state.mode)
  }

  def getFirstBracketOnLine(state: State) : (Int,Int) = {
    (state.position.line, getFirstBracket(state.contentLines(state.position.line), 0))
  }

  def getFirstBracket(line: String, position: Int) : Int = {
    if (position == line.length)
      return -1

    if (brackets.contains(line(position)))
      position
    else
      getFirstBracket(line, position + 1)
  }

  def findMatchingBracket(state: State): (Int,Int) = {
    val currentBracket = state.contentLines(state.position.line)(state.position.character)
    val bracketIndex = brackets.indexOf(currentBracket)

    if (bracketIndex % 2 == 0) {
      findMatchingBracketForward(state, state.position.line, state.position.character,
        currentBracket, brackets(bracketIndex + 1), -1)
    }
    else {
      findMatchingBracketBackward(state, state.position.line, state.position.character,
        currentBracket, brackets(bracketIndex - 1), -1)
    }
  }

  def findMatchingBracketForward(state: State, line: Int, char: Int, original: Char, opposite: Char, stack: Int) : (Int,Int) = {

    val current = state.contentLines(line)(char)
    if (current == opposite && stack == 0)
      return (line,char)

    if (LinePosition.endOfFile(state, line, char))
      return (-1,-1)

    val nextLine = if (LinePosition.lineEnd(state, line, char))
      line + 1 else line
    val nextChar = if (nextLine > line)
      0 else char + 1

    if (current == original)
      return findMatchingBracketForward(state, nextLine, nextChar, original, opposite, stack + 1)

    if (current == opposite)
      return findMatchingBracketForward(state, nextLine, nextChar, original, opposite, stack - 1)

    findMatchingBracketForward(state, nextLine, nextChar, original, opposite, stack)
  }

  def findMatchingBracketBackward(state: State, line: Int, char: Int, original: Char, opposite: Char, stack: Int) : (Int,Int) = {

    val current = state.contentLines(line)(char)
    if (current == opposite && stack == 0)
      return (line,char)

    if (LinePosition.startOfFile(line, char))
      return (-1,-1)

    if (LinePosition.lineStart(char))
      return findMatchingBracketBackward(state, line - 1, state.contentLines(line - 1).size - 1, original, opposite, stack)

    if (current == original)
      return findMatchingBracketBackward(state, line, char - 1, original, opposite, stack + 1)

    if (current == opposite)
      return findMatchingBracketBackward(state, line, char - 1, original, opposite, stack - 1)

    findMatchingBracketBackward(state, line, char - 1, original, opposite, stack)
  }

  def surroundingsContainBracket(state: State): Boolean = {
    brackets.contains(state.contentLines(state.position.line)(state.position.character))
  }

  def surroundingsContainBracket_realVIM(state: State): Boolean = {
    if (LinePosition.lineStart(state))
      return brackets.exists(x => x == state.contentLines(state.position.line)(0))

    if (LinePosition.lineEndExtended(state))
      brackets.exists(x => x == state.contentLines(state.position.line)(state.position.character - 1))
    else
      List(state.contentLines(state.position.line)(state.position.character - 1),
       state.contentLines(state.position.line)(state.position.character))
        .exists(y => brackets.exists(x => x == y))
  }

}