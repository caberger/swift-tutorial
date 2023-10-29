import Foundation
import MQTTNIO
import NIOCore
import SwiftUI

class MqttLocationPublisher {
    let client: MQTTClient
    let jsonEncoder = JSONEncoder()
    let topic = "mqttsensors/\(removeSpecialCharsFrom(text: UIDevice.current.name))/location"
    init() {
        client = MQTTClient(
            host: "localhost",
            port: 1883,
            identifier: "sensors",
            eventLoopGroupProvider: .createNew
        )
    }
    init(client: MQTTClient) {
        self.client = client
    }
    func connect() async -> (Bool, String?) {
        var msg: String?
        var ok = false
        do {
            try await client.connect()
            ok = true
        } catch {
            msg = "Error while connecting \(error)"
        }
        return (ok, msg)
    }
    func publish(location: LocationData) async throws {
        if let data = try? JSONEncoder().encode(location) {
            let json = String(data: data, encoding: .utf8)!
            try await client.publish(
                to: topic,
                payload: ByteBuffer(string: json),
                qos: .atLeastOnce
            )
        } else {
            print("cannot serialize")
        }
    }
}
private func removeSpecialCharsFrom(text: String) -> String {
    let okayChars = Set("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLKMNOPQRSTUVWXYZ1234567890-_")
    var result = ""
    for ch in text {
        result.append(okayChars.contains(ch) ? ch : "-")
    }
    return result
}
