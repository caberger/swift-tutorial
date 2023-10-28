import { Message } from "paho-mqtt"
import { start } from "../mqtt/mqtt"
import { produce } from "immer"
import { store } from "./dashboard-model"

start(onMessageArrived, setConnected)

function setConnected(connected: boolean) {
    const next = produce(store.getValue(), model => {
        model.connected = connected
    })
    store.next(next)

}
function onMessageArrived(message: Message) {
    console.log("message2 arrived", message.payloadString, message.destinationName)
}
