package at.htl.leonding.util.store;

import java.util.concurrent.CompletionException;
import java.util.function.Consumer;

import at.htl.leonding.util.immer.Immer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class Store<T> {
    public final BehaviorSubject<T> model;
    public final Immer<T> immer;

    protected Store(Class<? extends T> type) {
        try {
            var instance = type.newInstance();
            model = BehaviorSubject.createDefault(instance);
            immer = new Immer<>(type);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
    public void apply(Consumer<T> recipe) {
        model.onNext(immer.produce(model.getValue(), recipe));
    }
}
