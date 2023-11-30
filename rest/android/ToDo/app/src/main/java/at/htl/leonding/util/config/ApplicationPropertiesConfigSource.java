package at.htl.leonding.util.config;


import android.util.Log;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Properties;
import java.util.Set;

public class ApplicationPropertiesConfigSource implements ConfigSource {
    private static final String TAG = ApplicationPropertiesConfigSource.class.getName();
    private final String resourceName;
    private final int ordinal;
    private Properties properties = null;

    public ApplicationPropertiesConfigSource(String resourceName, int ordinal) {
        this.resourceName = resourceName;
        this.ordinal = ordinal;
    }
    private void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (var is = getClass().getResourceAsStream(resourceName)) {
                properties.load(is);
            } catch (Exception e) {
                Log.e(TAG, "failed to parse application.properties");
            }
        }
    }
    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getValue(String propertyName) {
        return null;
    }
    @Override
    public String getName() {
        return null;
    }
}
