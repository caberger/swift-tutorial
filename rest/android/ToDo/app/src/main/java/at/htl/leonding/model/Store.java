package at.htl.leonding.model;

import javax.inject.Singleton;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

@Singleton
public record Store(
        BehaviorSubject<ToDoModel> toDoModel
) {
}
