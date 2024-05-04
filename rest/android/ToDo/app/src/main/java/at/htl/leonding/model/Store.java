package at.htl.leonding.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.util.immer.Immer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/** This is our Storage area for <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">single source of truth</a> {@link Model}. */
@Singleton
public class Store {
    public final BehaviorSubject<Model> model = BehaviorSubject.createDefault(new Model());
    public final Immer<Model> immer = new Immer<>(Model.class);

    @Inject
    Store() {
    }
    public void set(ToDo[] toDos) {
        model.onNext(immer.produce(model.getValue(), model -> model.toDos = toDos));
    }
}
