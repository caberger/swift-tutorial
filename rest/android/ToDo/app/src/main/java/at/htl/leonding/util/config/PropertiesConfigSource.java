package at.htl.leonding.util.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class PropertiesConfigSource implements ConfigSource {
    private final int ordinal;
    private final String name;
    protected Properties properties = new Properties();

    protected PropertiesConfigSource(int ordinal, String name) {
        this.ordinal = ordinal;
        this.name = name;
    }
    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet().stream().map(key -> key.toString()).collect(Collectors.toSet());
    }
    @Override
    public int getOrdinal() {
        return ordinal;
    }
    @Override
    public String getValue(String propertyName) {
        return properties.getProperty(propertyName);
    }
    @Override
    public String getName() {
        return name;
    }
}
