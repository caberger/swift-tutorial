package at.ac.htl.sensors.mqtt;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionException;

import at.ac.htl.sensors.model.Mapper;
import at.ac.htl.sensors.model.Model;

public class MqttLocationPublisher<T extends Object> {
    private static final String TAG = MqttLocationPublisher.class.getSimpleName();
    private Mapper<T> mapper;
    private MqttClient client;
    private String topic;

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
    public MqttLocationPublisher(Class<? extends T> clazz) {
        mapper = new Mapper<T>(clazz);
    }
    public Config createConfiguration() {
        return new Config();
    }
    public void connect(Config config, String topic) {
        this.topic = topic;
        var persistence = new MemoryPersistence();
        try {
            Log.i(TAG, String.format("connecting to Mqtt service as %s", config.toString()));
            client = new MqttClient(config.broker, config.clientId, persistence);
            client.connect();
        } catch (MqttException e) {
            Log.e(TAG, "failed to connect", e);
            throw new RuntimeException(e);
        }
    }
    public void publish(T data) {
        var json = mapper.toResource(data);
        var body = json.getBytes(StandardCharsets.UTF_8);
        var message = new MqttMessage(body);
        try {
            client.publish(topic, message);
        } catch (MqttException e) {
            Log.i(TAG, "failed to publish", e);
            throw new CompletionException(e);
        }
    }
}
