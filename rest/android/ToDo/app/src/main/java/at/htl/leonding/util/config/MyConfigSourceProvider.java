package at.htl.leonding.util.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Arrays;

public class MyConfigSourceProvider implements org.eclipse.microprofile.config.spi.ConfigSourceProvider {
    private final ConfigSource[] configSources;

    public MyConfigSourceProvider(String resourceLocation) {
        var applicationPropertiesConfigSource = new ApplicationPropertiesConfigSource("application.properties", 200);
        var microprofileConfigSource = new ApplicationPropertiesConfigSource("META-INF/microprofile-config.properties", ConfigSource.DEFAULT_ORDINAL);
        configSources = new ConfigSource[] {
                microprofileConfigSource,
                applicationPropertiesConfigSource
        };
    }
    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
        return () -> Arrays.stream(configSources).iterator();
    }
}
