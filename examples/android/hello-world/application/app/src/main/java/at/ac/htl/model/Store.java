package at.ac.htl.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.ac.htl.util.GenericStore;
import at.ac.htl.util.ObjectMapperFactory;

@Singleton
public class Store extends GenericStore<Model> {
    @Inject
    public Store(ObjectMapperFactory objectMapperFactory, InitialState initialState) {
        super(objectMapperFactory, initialState, Model.class);
    }
}

