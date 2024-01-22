package at.ac.htl.util;

import java.util.function.Consumer;

import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class GenericStore<T> {
    public Mapper<T> mapper;
    public BehaviorSubject<T> subject;

    public GenericStore(ObjectMapperFactory objectMapperFactory, T initialState, Class<? extends T> clazz) {
        subject = BehaviorSubject.createDefault(initialState);
        mapper = objectMapperFactory.createMapper(clazz);
    }
    /** deep clone the model and call the recipe to apply the changes to the clone and set this as the next state
     * @param recipe the recipe that modifies the clone to give the new model.
     */
    public void produce(Consumer<T> recipe) {
        final var draft = mapper.clone(subject.getValue());
        recipe.accept(draft);
        subject.onNext(draft);
    }
}
