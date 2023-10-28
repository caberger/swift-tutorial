import { Message } from "paho-mqtt"
import { start } from "../mqtt/mqtt"

function onMessageArrived(message: Message) {
    console.log("message2 arrived", message.payloadString, message.destinationName)
}
start(onMessageArrived)
