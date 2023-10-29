/** submit demo values for simulating a real box
*/

import { client } from "./mqtt"
import {  } from "../model"
import { LocationData } from "../model/dashboard-model"

setInterval(send, 1000)

function send() {
    if (client.isConnected()) {
        sendDummyData()
    } else {
        console.log("cannot send data, client not connected")
    }
}
function sendDummyData() {
    const topic = `$mqttsensors/${createRandomPhoneName()}/location`
    const longitude = Math.round(Math.random() * 360)
    const latitude = Math.round(Math.random() * 360)
    const val: LocationData = {
        longitude,
        latitude
    }
    const payload = JSON.stringify(val, undefined, 4)
    //console.log("send", payload, "to topic", topic)
    client.send(topic, payload)
}
function createRandomPhoneName() {
    const roomNumber = 6 + Math.floor(Math.random() * 10)
    return `iPhone-${roomNumber}-Pro`
}
