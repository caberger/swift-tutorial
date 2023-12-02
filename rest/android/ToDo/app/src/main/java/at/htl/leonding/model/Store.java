package at.htl.leonding.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/** This is our Storage area for <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">single source of truth</a> {@link ToDoModel}. */
@Singleton
public class Store {
    private final BehaviorSubject<ToDoModel> toDoModel;
    @Inject
    Store() {
        toDoModel = BehaviorSubject.createDefault(new ToDoModel());
    }
    public void next(ToDo[] todos) {
        toDoModel.onNext(new ToDoModel(List.of(todos)));
    }

    public BehaviorSubject<ToDoModel> getToDoModel() {
        return toDoModel;
    }
}
