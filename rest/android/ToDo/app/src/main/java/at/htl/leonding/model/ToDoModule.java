package at.htl.leonding.model;

import javax.inject.Singleton;

import at.htl.leonding.Configuration;
import at.htl.leonding.util.ApplicationPropertiesLoader;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/** <a href="https://en.wikipedia.org/wiki/Dependency_injection">Dependency Injection</a> Module to provide those classes that need some initialization work. */
@Module
@InstallIn(SingletonComponent.class)
public class ToDoModule {
    @Provides
    @Singleton
    public Configuration provideConfiguration() {
        return new ApplicationPropertiesLoader().load().orElse(new Configuration(""));
    }
    @Provides
    @Singleton
    public Store provideStore() {
        return new Store(BehaviorSubject.createDefault(new ToDoModel()));
    }
}
