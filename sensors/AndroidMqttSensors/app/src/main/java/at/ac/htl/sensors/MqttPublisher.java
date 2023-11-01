package at.ac.htl.sensors;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import at.ac.htl.sensors.model.Model;
import at.ac.htl.sensors.mqtt.MqttLocationPublisher;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MqttPublisher {
    private MqttLocationPublisher<Model.LocationData> publisher;
    private Disposable subscription;

    public void connect(Context context) {
        publisher = new MqttLocationPublisher<>(Model.LocationData.class);
        final var config = publisher.createConfiguration();
        var deviceModel = Build.MODEL;
        var deviceBrand = Build.MANUFACTURER;
        var deviceName = Build.DEVICE;
        config.clientId = String.format("%s-%s-%s", deviceModel, deviceBrand, deviceName);
        var model = Build.MODEL;
        var topic = String.format("%s/%s/location", model, config.clientId);
        publisher.connect(config, topic);
    }
    public void startPublishing(Observable<Model.LocationData> data) {
        subscription = data.subscribe(publisher::publish);
    }
    public void stopPublishing() {
        subscription.dispose();
    }
}
