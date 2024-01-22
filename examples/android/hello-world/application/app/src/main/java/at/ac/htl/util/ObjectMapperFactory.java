package at.ac.htl.util;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ObjectMapperFactory {
    @Inject
    public ObjectMapperFactory() {
    }
    public <T> Mapper<T> createMapper(Class<? extends T> clazz) {
        return new Mapper(clazz);
    }
}
