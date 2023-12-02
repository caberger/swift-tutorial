package at.htl.leonding.util.config;


import android.util.Log;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationPropertiesConfigSource implements ConfigSource {
    private static final String TAG = ApplicationPropertiesConfigSource.class.getName();
    private final String resourceName;
    private final int ordinal;
    private Properties properties = new Properties();

    public ApplicationPropertiesConfigSource(String resourceName, int ordinal, ClassLoader forClassLoader) {
        this.resourceName = resourceName;
        this.ordinal = ordinal;
        this.loadProperties(forClassLoader);
    }
    private void loadProperties(ClassLoader forClassLoader) {
        try (var is = forClassLoader.getResourceAsStream(resourceName)) {
            properties.load(is);
        } catch (Exception e) {
            Log.e(TAG, "failed to parse application.properties");
        }
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
        return resourceName;
    }
}
