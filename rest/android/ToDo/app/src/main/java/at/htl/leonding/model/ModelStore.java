package at.htl.leonding.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.leonding.util.store.Store;

/** This is our Storage area for <a href="https://redux.js.org/understanding/thinking-in-redux/three-principles">single source of truth</a> {@link Model}. */
@Singleton
public class ModelStore extends Store<Model> {
    @Inject
    ModelStore() {
        super(Model.class, new Model());
    }

    public void setTodos(ToDo[] toDos) {
        apply(model -> model.toDos = toDos);
    }
    public void selectTab(int tabIndex) {
        apply(model -> model.uiState.selectedTab = tabIndex);
    }
}
