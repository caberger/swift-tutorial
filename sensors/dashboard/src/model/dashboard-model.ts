import { BehaviorSubject } from "rxjs"
import { MqttConfig } from "../mqtt"

export interface LocationData {
    longitude: number,
    latitude: number
}
export interface Device {
    readonly name: string
    readonly lastValueReceivedAt: Date
    location: LocationData
}

export interface DashboardModel {
    readonly mqttConfig: MqttConfig
    readonly connected: boolean // are we connected to mqtt?
    readonly devices: Map<string, Device>
}

const initialState: DashboardModel = {
    connected: false,
    devices: new Map(),
    mqttConfig: undefined
}

export const store = new BehaviorSubject<DashboardModel>(initialState)

