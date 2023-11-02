package at.ac.htl.sensors.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import at.ac.htl.sensors.model.Mapper;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class MqttPublisher<T extends Object> {
    private static final String TAG = MqttPublisher.class.getSimpleName();
    private Mapper<T> mapper;
    private MqttClient client;
    private String topic;
    private BehaviorSubject<Boolean> connectedObservable = BehaviorSubject.createDefault(false);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public class Config {
        public String broker = "tcp://10.0.2.2:1883";
        public String clientId = null;

        private Config() {}

        @Override
        public String toString() {
            return "Config{" +
                    "broker='" + broker + '\'' +
                    ", clientId='" + clientId + '\'' +
                    '}';
        }
    }
    public Observable<Boolean> connected() {
        return connectedObservable;
    }
    public MqttPublisher(Class<? extends T> clazz) {
        mapper = new Mapper<T>(clazz);
    }
    public Config createConfiguration() {
        return new Config();
    }

    private void scheduleConnectionKeeper(Config config) {
        Runnable keeper = () -> {
            Log.i(TAG, "check for valid connection.");
            if (client == null) {
                try {
                    client = new MqttClient(config.broker, config.clientId, new MemoryPersistence());
                } catch (MqttException e) {
                    Log.e(TAG, "failed to create client", e);
                }
            }
            if (client != null) {
                if (!client.isConnected()) {
                    Log.i(TAG, String.format("connecting to Mqtt service as %s", config.toString()));
                    try {
                        client.connect();
                    } catch (MqttException e) {
                        Log.i(TAG, "failed to connect", e);
                    }
                }
                connectedObservable.onNext(client.isConnected());
            }
        };
        scheduler.scheduleAtFixedRate(keeper, 0, 2, TimeUnit.SECONDS);
    }
    public void connect(Config config, String topic) {
        assert(client == null);
        this.topic = topic;
        scheduleConnectionKeeper(config);
    }
    public void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            Log.e(TAG, "failed to disconnect", e);
            throw new RuntimeException(e);
        }
        connectedObservable.onNext(client.isConnected());
        scheduler.shutdown();
    }
    private boolean isOK() {
        return client != null && client.isConnected();
    }
    public void publish(T data) {
        var json = mapper.toResource(data);
        var body = json.getBytes(StandardCharsets.UTF_8);
        var message = new MqttMessage(body);
        try {
            if (isOK()) {
                client.publish(topic, message);
            } else {
                Log.i(TAG, "client is not connected");
            }
        } catch (Exception e) {
            Log.e(TAG, "failed to publish", e);
        }
    }
}
