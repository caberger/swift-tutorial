package at.htl.leonding.util.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Arrays;

public class MyConfigSourceProvider implements org.eclipse.microprofile.config.spi.ConfigSourceProvider {
    private ConfigSource[] configSources;

    public MyConfigSourceProvider() {
    }
    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
        var applicationPropertiesConfigSource = new ApplicationPropertiesConfigSource("application.properties", 200, forClassLoader);
        var microprofileConfigSource = new ApplicationPropertiesConfigSource("META-INF/microprofile-config.properties", ConfigSource.DEFAULT_ORDINAL, forClassLoader);
        if (configSources == null) {
            configSources = new ConfigSource[] {
                    microprofileConfigSource,
                    applicationPropertiesConfigSource
            };
        }
        return () -> Arrays.stream(configSources).iterator();
    }
}
