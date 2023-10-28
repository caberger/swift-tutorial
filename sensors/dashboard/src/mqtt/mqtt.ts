/**
 * paho javascript client, see https://github.com/eclipse/paho.mqtt.javascript#readme
 */
import {Client, ErrorWithInvocationContext, MQTTError, Message} from "paho-mqtt"

type MessageReceivedHandler = (message: Message) => void
type ConnectionStateCallback = (connected: boolean) => void

export interface ConnectionParameters {
    config: MqttConfig
    messageHandler: MessageReceivedHandler,
    statusCallback: ConnectionStateCallback,
    filter: string
}
export interface MqttConfig {
    host: string
    port: number
    clientId: string
}
export let client: Client

const defaultConfig: MqttConfig = {
    host: "localhost",
    port: 9001,
    clientId: "mqttdashboard",
}
let params: ConnectionParameters = {
    messageHandler: onMessageArrived,
    statusCallback: defaultConnectedState,
    filter: "#",
    config: defaultConfig
}

let timer: NodeJS.Timeout

export function start(config: ConnectionParameters) {
    params = config
    connect()
}

function connect() {
    const mqttConfig = params.config
    client = new Client(mqttConfig.host, mqttConfig.port, mqttConfig.clientId)
    console.log(`connecting to ${mqttConfig.host}:${mqttConfig.port}/${mqttConfig.clientId}`)
    client.onMessageArrived = params.messageHandler
    client.onConnectionLost = onConnectionLost

    params.statusCallback(false)
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
    console.log("connected to", params.config)
    console.log("subscribing to", params.filter)
    client.subscribe(params.filter)
    params.statusCallback(true)
}
function onMessageArrived(message: Message) {
    console.log("message arrived", message.payloadString, message.destinationName)
}
function checkConnection() {
    console.log("connection check", client, "isconn", client?.isConnected())
    if (!client || !client.isConnected()) {
        params.statusCallback(false)
        client = null
        console.log("not connected")
        connect()
    }
}
function onConnectionLost(error: MQTTError) {
    console.log("connection lost", error)
    params.statusCallback(false)
}
function onConnectError(e: ErrorWithInvocationContext) {
    console.log("failed to connect: ", e)
}
function defaultConnectedState(connected: boolean) {
    console.log("connection state changed to", connected)
}
