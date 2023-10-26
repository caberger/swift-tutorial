
import Foundation

class ThingWorksClient {
    let client = MQTTClient(
        configuration: .init(
            target: .host("127.0.0.1", port: 1883)
        ),
        eventLoopGroupProvider: .createNew
    )
}
