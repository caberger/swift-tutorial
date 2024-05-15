package at.htl.leonding.util.store;

import java.util.concurrent.CompletionException;
import java.util.function.Consumer;

import at.htl.leonding.util.immer.Immer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/** Base class for imlementations using a single source of truth aproach.
 *
 * @param <T> the class of the ReadOnly Single Source of Truth.
 */
public class StoreBase<T> {
    public final BehaviorSubject<T> pipe;
    public final Immer<T> immer;

    protected StoreBase(Class<? extends T> type, T initialState) {
        try {
            pipe = BehaviorSubject.createDefault(initialState);
            immer = new Immer<T>(type);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }
    public void apply(Consumer<T> recipe) {
        pipe.onNext(immer.produce(pipe.getValue(), recipe));
    }
}
