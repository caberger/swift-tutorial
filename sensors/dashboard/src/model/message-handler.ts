import { Message } from "paho-mqtt"
import { start, MqttConfig } from "../mqtt"
import { produce } from "immer"
import { Device, LocationData, store } from "./dashboard-model"

const pattern = /mqttsensors\/(.*)\/location/
const mqttConfig: MqttConfig = {
    host: "localhost",
    port: 9001,
    clientId: "mqttdashboard",
}

function startMqtt() {
    const nextState = produce(store.getValue(), model => {
        model.mqttConfig = mqttConfig
    })
    store.next(nextState)
    start({
        messageHandler: onMessageArrived,
        statusCallback: setConnected,
        filter: "mqttsensors/+/location",
        config: mqttConfig
    })
}

startMqtt()

function setConnected(connected: boolean) {
    const next = produce(store.getValue(), model => {
        model.connected = connected
    })
    store.next(next)

}
function onMessageArrived(message: Message) {
    const matches = message.destinationName.match(pattern)
    const name = matches[1]
    const location: LocationData = JSON.parse(message.payloadString)
    let device: Device = {
        name,
        lastValueReceivedAt: new Date(),
        location
    }
    console.log("message arrived", device)
    const nextState = produce(store.getValue(), model => {
        model.devices.set(name, device)
    })
    store.next(nextState)
}
