package at.htl.leonding.util.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.ConfigSourceProvider;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

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
        var classLoader = getClass().getClassLoader();
        var sources = new HashMap<Integer, ConfigSource>();
        if (config == null) {
            var provider = ServiceLoader.load(ConfigSourceProvider.class);
            for (var configSourceProvider: provider) {
                for (var configSource: configSourceProvider.getConfigSources(classLoader)) {
                    sources.put(configSource.getOrdinal(), configSource);
                }
            }
            var keys = sources
                    .keySet()
                    .stream()
                    .collect(Collectors.toList());
            keys.sort((l, r) -> l - r);
            var configSortedByPriority = keys.stream().map(key -> sources.get(key)).collect(Collectors.toList());
            config = new MergedConfig(configSortedByPriority);
        }
        return config;
    }
}
class MergedConfig implements Config {
    Map<String, String> properties = new HashMap<>();
    final List<ConfigSource> configSources;

    MergedConfig(List<ConfigSource> configSourcesSortedByPriority) {
        this.configSources = configSourcesSortedByPriority;
        configSourcesSortedByPriority.forEach(config -> {
            var names = config.getPropertyNames();
            names.forEach(name -> {
                var value = config.getValue(name);
                properties.put(name, value);
            });
        });

    }
    // other classes should specialze T... to be done?
    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {
        return (T)properties.get(propertyName);
    }
    @Override
    public ConfigValue getConfigValue(String propertyName) {
        //var value = new ConfigValue();
        return null;
    }
    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        return Optional.ofNullable(getValue(propertyName, propertyType));
    }
    @Override
    public Iterable<String> getPropertyNames() {
        return properties.keySet().stream().collect(Collectors.toList());
    }
    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return configSources;
    }
    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> forType) {
        return Optional.empty();
    }
    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }
}
