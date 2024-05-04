package at.htl.leonding.util.resteasy;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.URLConnectionClientEngineBuilder;
import org.jboss.resteasy.client.jaxrs.internal.LocalResteasyProviderFactory;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import jakarta.ws.rs.client.ClientBuilder;

@Singleton
public class RestApiClientBuilder {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Inject
    public RestApiClientBuilder() {
    }
    public <T> T build(Class <? extends T> type, String url) {
        ResteasyProviderFactory.setRegisterBuiltinByDefault(false);
        var factory = ResteasyProviderFactory.getInstance();
        factory.registerProvider(BodyReader.class, true);
        factory.registerProvider(BodyWriter.class, true);
        var builder = (ResteasyClientBuilder) ClientBuilder
                .newBuilder();
        builder.scheduledExecutorService(scheduledExecutorService);
        builder.executorService(executorService, false);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.providerFactory(new LocalResteasyProviderFactory(factory));
        var httpEngine = new URLConnectionClientEngineBuilder().resteasyClientBuilder(builder).build();
        builder.httpEngine(httpEngine);
        var client = builder.build();

        ResteasyWebTarget target = client.target(url);
        var restClient = target.proxy(type);
        return restClient;
    }
}
