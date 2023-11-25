package at.htl.leonding.model;

import android.util.Log;

import java.util.Properties;

import javax.inject.Singleton;

import at.htl.leonding.Configuration;
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
        return parseProperties();
    }
    @Provides
    @Singleton
    public static Store provideStore() {
        return new Store(BehaviorSubject.createDefault(new ToDoModel()));
    }

    Configuration parseProperties() {
        Configuration configuration = null;
        try (var is = getClass().getResourceAsStream("/application.properties")) {
            var props = new Properties();
            props.load(is);
            var url = props.getProperty("json.placeholder.baseurl");
            configuration = new Configuration(url);
        } catch (Exception e) {
            Log.e(TAG, "failed to parse application.properties");
        }
        return configuration;
    }
}
