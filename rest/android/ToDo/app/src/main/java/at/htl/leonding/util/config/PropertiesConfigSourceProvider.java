package at.htl.leonding.util.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Arrays;

public class PropertiesConfigSourceProvider implements org.eclipse.microprofile.config.spi.ConfigSourceProvider {
    private static final String TAG = PropertiesConfigSourceProvider.class.getName();
    private ConfigSource[] configSources;

    public PropertiesConfigSourceProvider() {
    }
    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
        if (configSources == null) {
            var applicationPropertiesConfigSource = new ClassPathResourceLoaderPropertiesConfigSource("application.properties", 200, forClassLoader);
            var microprofileConfigSource = new ClassPathResourceLoaderPropertiesConfigSource("META-INF/microprofile-config.properties", ConfigSource.DEFAULT_ORDINAL, forClassLoader);
            configSources = new ConfigSource[] {
                    microprofileConfigSource,
                    applicationPropertiesConfigSource
            };
        }
        return () -> Arrays.stream(configSources).iterator();
    }
}
