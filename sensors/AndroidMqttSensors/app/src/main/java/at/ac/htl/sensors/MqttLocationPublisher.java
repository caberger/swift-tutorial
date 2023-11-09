package at.ac.htl.sensors;

import android.os.Build;

import at.ac.htl.sensors.model.Model;
import at.ac.htl.sensors.mqtt.MqttPublisher;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class MqttLocationPublisher {
    private MqttPublisher<Model.LocationData> publisher = new MqttPublisher<>(Model.LocationData.class);
    private Disposable subscription;
    private Subject<Boolean> connectedObservable = BehaviorSubject.createDefault(false);

    public void connect() {
        publisher = new MqttPublisher<>(Model.LocationData.class);
        final var config = publisher.createConfiguration();
        var deviceName = Build.DEVICE;
        config.clientId = String.format("%s-%s-%s", Build.MODEL, Build.MANUFACTURER, deviceName);
        var topic = String.format("mqttsensors/%s/location", deviceName);
        publisher.connect(config, topic);
        publisher.isConnected().subscribe(connected -> connectedObservable.onNext(connected));
    }
    public Observable<Boolean> isConnected() {
        return connectedObservable;
    }
    public void startPublishing(Observable<Model.LocationData> data) {
        subscription = data.subscribe(publisher::publish);
    }
    public void stopPublishing() {
        subscription.dispose();
        publisher.disconnect();
    }
}
