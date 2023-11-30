package at.htl.leonding.util.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.ServiceLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ConfigModule {
    private Config config = null;
    @Provides
    @Singleton
    public Config provideConfiguration() {
        if (config == null) {
            var provider = ServiceLoader.load(MyConfigSourceProvider.class);
            var it = provider.iterator();
            var lowest = Integer.MAX_VALUE;
            ConfigSource lowestSource = null;
            while(it.hasNext()) {
                var configSource = it.next();
                //if (configSource.)
            }
//            config = provider.
        }
        return config;
    }
}
