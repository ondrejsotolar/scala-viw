package viw.conditions
import viw.internals.State

class Not(val condition: Condition) extends Condition {
  override def satisfy(state: State): Boolean =
    !condition.satisfy(state)
}
