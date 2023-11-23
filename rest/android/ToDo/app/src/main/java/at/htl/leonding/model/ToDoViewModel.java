package at.htl.leonding.model;

import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class ToDoViewModel extends ViewModel {
    private BehaviorSubject<ToDoModel> _store = BehaviorSubject.createDefault(new ToDoModel());

    public BehaviorSubject<ToDoModel> getStore() {
        return _store;
    }
}
