package at.htl.leonding.model;

import java.util.List;
import javax.inject.Singleton;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/** This is our Storage area for <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">single source of truth</a> {@link ToDoModel}. */
@Singleton
public record Store(
        BehaviorSubject<ToDoModel> toDoModel
) {
    public void next(ToDo[] todos) {
        toDoModel.onNext(new ToDoModel(List.of(todos)));
    }
}
