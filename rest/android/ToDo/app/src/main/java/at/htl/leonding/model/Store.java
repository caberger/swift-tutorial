package at.htl.leonding.model;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

@Singleton
public record Store(
        BehaviorSubject<ToDoModel> toDoModel
) {
    public void next(ToDo[] todos) {
        toDoModel.onNext(new ToDoModel(List.of(todos)));
    }
}
