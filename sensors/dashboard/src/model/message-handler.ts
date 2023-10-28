import { Message } from "paho-mqtt"
import { start, MqttConfig } from "../mqtt"
import { produce } from "immer"
import { store } from "./dashboard-model"

export const mqttConfig: MqttConfig = {
    host: "localhost",
    port: 9001,
    clientId: "mqttdashboard",
}
start({
    messageHandler: onMessageArrived,
    statusCallback: setConnected,
    filter: "mqttsensors/+/location",
    config: mqttConfig
})

function setConnected(connected: boolean) {
    const next = produce(store.getValue(), model => {
        model.connected = connected
    })
    store.next(next)

}
function onMessageArrived(message: Message) {
    console.log("message2 arrived", message.payloadString, message.destinationName)
}
