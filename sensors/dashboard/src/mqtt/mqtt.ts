/**
 * paho javascript client, see https://github.com/eclipse/paho.mqtt.javascript#readme
 */
import {Client, ErrorWithInvocationContext, MQTTError, Message} from "paho-mqtt"
import { mqttConfig } from "./config"
export {mqttConfig}

export type MessageReceivedHandler = (message: Message) => void
export type ConnectionStateCallback = (connected: boolean) => void

export interface MqttConfig {
    host: string
    port: number
    clientId: string
}
export let client: Client

let messageReceivedHander: MessageReceivedHandler = onMessageArrived
let connectionStateCallback = setConnected

let timer: NodeJS.Timeout

export function start(messageHandler: MessageReceivedHandler, connected: ConnectionStateCallback) {
    messageReceivedHander = messageHandler
    connectionStateCallback = connected
    connect()
}

function connect() {
    client = new Client(mqttConfig.host, mqttConfig.port, mqttConfig.clientId)
    console.log(`connecting to ${mqttConfig.host}:${mqttConfig.port}/${mqttConfig.clientId}`)
    client.onMessageArrived = messageReceivedHander
    client.onConnectionLost = onConnectionLost

    setConnected(false)
    try {
        client.connect({
            onSuccess: onConnect,
            onFailure: onConnectError
        })
    } catch(e) {
        console.warn("failed to connect", e)
    }
    if (timer) {
        clearInterval(timer)
    }
    timer = setInterval(() => checkConnection(), 5000)
}

function onConnect() {
    console.log("connected to", mqttConfig)
    const filter = "mqttsensors/#"
    console.log("subscribing to", filter)
    client.subscribe(filter)
    console.log("subscribed to", filter)
    setConnected(true)
}
function onMessageArrived(message: Message) {
    console.log("message arrived", message.payloadString, message.destinationName)
}
function checkConnection() {
    console.log("connection check", client, "isconn", client?.isConnected())
    if (!client || !client.isConnected()) {
        setConnected(false)
        client = null
        console.log("not connected")
        connect()
    }
}
function onConnectionLost(error: MQTTError) {
    console.log("connection lost", error)
    setConnected(false)
}
function onConnectError(e: ErrorWithInvocationContext) {
    console.log("failed to connect: ", e)
}
function setConnected(connected: boolean) {
    console.log("connection state changed to", connected)
}
