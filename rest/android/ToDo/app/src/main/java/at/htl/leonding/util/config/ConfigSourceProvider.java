package at.htl.leonding.util.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Arrays;

public class ConfigSourceProvider implements org.eclipse.microprofile.config.spi.ConfigSourceProvider {
    private final ConfigSource[] configSources;

    public ConfigSourceProvider(String resourceLocation) {
        var applicationPropertiesConfigSource = new ApplicationPropertiesConfigSource("application.properties", 200);
        var microprofileConfigSource = new ApplicationPropertiesConfigSource("META-INF/microprofile-config.properties", ConfigSource.DEFAULT_ORDINAL);
        configSources = new ConfigSource[] {
                microprofileConfigSource,
                applicationPropertiesConfigSource
        };
    }
    @Override
    public Iterable<ConfigSource> getConfigSources(ClassLoader forClassLoader) {
        var iterator = Arrays.stream(configSources).iterator();
        return ite
    }
}
