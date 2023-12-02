package at.htl.leonding.util.config;


import android.util.Log;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassPathResourceLoaderPropertiesConfigSource extends PropertiesConfigSource {
    private static final String TAG = ClassPathResourceLoaderPropertiesConfigSource.class.getName();
    private final String resourceName;
    private final int ordinal;

    public ClassPathResourceLoaderPropertiesConfigSource(String resourceName, int ordinal, ClassLoader forClassLoader) {
        super(ordinal, resourceName);
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
}
