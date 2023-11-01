package at.ac.htl.sensors.mqtt;

import android.content.Context;
import android.provider.Settings;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import at.ac.htl.sensors.model.Mapper;
import at.ac.htl.sensors.model.Model;

class Config {
    public String broker = "tcp://localhost:1883";
    public String clientId = "AndroidMQTT";
    public String topic = "location";
}

public class MqttPublisher {
    final private Mapper<Model.LocationData> mapper = new Mapper<>(Model.LocationData.class);
    private MqttClient client;

    public void connect(Context context) {
        var config = new Config();
        config.clientId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        //config.topic = String.format("%s/")
        var persistence = new MemoryPersistence();
        try {
            client = new MqttClient(config.broker, config.clientId, persistence);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
    public void publish(Model.LocationData data) {
    }
}
