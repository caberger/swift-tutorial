package at.htl.leonding.util.config;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Properties;

public abstract class PropertiesConfigSource implements ConfigSource {
    private Properties properties = new Properties();
}
