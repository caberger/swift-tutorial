package at.htl.leonding.model;

import javax.inject.Singleton;

import at.htl.leonding.Configuration;
import at.htl.leonding.util.ApplicationPropertiesLoader;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

@Module
@InstallIn(SingletonComponent.class)
public class ToDoModule {
    private static final String TAG = ToDoModule.class.getName();

    @Provides
    @Singleton
    public Configuration provideConfiguration() {
        return new ApplicationPropertiesLoader().load().orElse(new Configuration(""));
    }
    @Provides
    @Singleton
    public static Store provideStore() {
        return new Store(BehaviorSubject.createDefault(new ToDoModel()));
    }
}
