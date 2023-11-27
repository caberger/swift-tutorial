package at.htl.leonding;

import javax.inject.Singleton;

/** The parameters of your application */
@Singleton
public record Configuration(String baseUrl) {
}
