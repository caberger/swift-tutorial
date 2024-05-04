package at.htl.leonding.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.util.immer.Immer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/** This is our Storage area for <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">single source of truth</a> {@link Model}. */
@Singleton
public class Store {
    public final BehaviorSubject<Model> model;
    public final Immer<Model> immer;

    @Inject
    Store() {
        model = BehaviorSubject.createDefault(new Model());
        immer = new Immer<>(Model.class);
    }
    public void set(ToDo[] toDos) {
        model.onNext(immer.produce(model.getValue(), model -> model.toDos = toDos));
    }
}
