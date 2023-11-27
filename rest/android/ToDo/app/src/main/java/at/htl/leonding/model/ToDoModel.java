package at.htl.leonding.model;
import java.util.List;

/** Our read only <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">single source of truth</a> model */
public record ToDoModel (
  List<ToDo> toDos
)
{
    public ToDoModel() {
        this(List.of());
    }
}
