import Foundation
import MQTTNIO

class MqttPublisher {
    let client = MQTTClient(
        host: "mqtt.eclipse.org",
        port: 1883,
        identifier: "My Client",
        eventLoopGroupProvider: .createNew
    )
}
