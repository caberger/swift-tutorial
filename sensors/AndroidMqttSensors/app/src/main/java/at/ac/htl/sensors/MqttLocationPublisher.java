package at.ac.htl.sensors;

import android.content.Context;
import android.os.Build;

import at.ac.htl.sensors.model.Model;
import at.ac.htl.sensors.mqtt.MqttPublisher;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MqttLocationPublisher {
    private MqttPublisher<Model.LocationData> publisher = new MqttPublisher<>(Model.LocationData.class);
    private Disposable subscription;

    public void connect() {
        publisher = new MqttPublisher<>(Model.LocationData.class);
        final var config = publisher.createConfiguration();
        var deviceModel = Build.MODEL;
        var deviceBrand = Build.MANUFACTURER;
        var deviceName = Build.DEVICE;
        config.clientId = String.format("%s-%s-%s", deviceModel, deviceBrand, deviceName);
        var topic = String.format("%s/location", deviceName);
        publisher.connect(config, topic);
    }
    public Observable<Boolean> connected() {
        return publisher.connected();
    }

    public void startPublishing(Observable<Model.LocationData> data) {
        subscription = data.subscribe(publisher::publish);
    }
    public void stopPublishing() {
        subscription.dispose();
        publisher.disconnect();
    }
}
