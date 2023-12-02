package at.htl.leonding;

import org.eclipse.microprofile.config.Config;

import javax.inject.Inject;
import javax.inject.Singleton;

/** The parameters of your application */
@Singleton
public class Configuration {
    public static final String JSON_PLACEHOLDER_BASE_URL_SETTING = "json.placeholder.baseurl";
    private final String baseUrl;

    @Inject
    public Configuration(Config config) {
        this.baseUrl = config.getValue(JSON_PLACEHOLDER_BASE_URL_SETTING, String.class);
    }
    public String getBaseUrl() {
        return baseUrl;
    }
}
