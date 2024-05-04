package at.htl.leonding.util.immer;

import java.util.function.Consumer;

import at.htl.leonding.util.model.Mapper;

/** Immer simplifies handling immutable data structures.
 * @see <a>https://redux.js.org/understanding/thinking-in-redux/motivation</a>
 * @see <a>https://immerjs.github.io/immer/</a>
 *
 * @param <T> The type of the baseState
 */
public class Immer<T> {
    public final Mapper<T> mapper;

    public Immer(Class<? extends T> type) {
        mapper = new Mapper<T>(type);
    }
    public T produce(T t, Consumer<T> recipe) {
        var model = mapper.clone(t);
        recipe.accept(model);
        return model;
    }
}
