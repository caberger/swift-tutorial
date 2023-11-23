package at.htl.leonding.model;
import java.util.List;

public record ToDoModel (
  List<ToDo> toDos
)
{
    public ToDoModel() {
        this(List.of());
    }
}
