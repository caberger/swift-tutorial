package at.htl.leonding.util;

import android.util.Log;

import java.util.Optional;
import java.util.Properties;

import at.htl.leonding.Configuration;

/** Loader for the properties stored in src/main/resources/application.properties */
public class ApplicationPropertiesLoader {
    private static final String TAG = ApplicationPropertiesLoader.class.getName();

    public Optional<Configuration> load() {
        Configuration configuration = null;
        try (var is = getClass().getResourceAsStream("/application.properties")) {
            var props = new Properties();
            props.load(is);
            var url = props.getProperty("json.placeholder.baseurl");
            configuration = new Configuration(url);
        } catch (Exception e) {
            Log.e(TAG, "failed to parse application.properties");
        }
        return Optional.ofNullable(configuration);
    }
}
