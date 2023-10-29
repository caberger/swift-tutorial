import {html, render} from "lit-html"
import {DashboardModel, store} from "../model"
import { distinctUntilChanged, filter, map } from "rxjs"
import { styles } from "../styles/styles"

import "./connection-icon"
import { Device } from "../model/dashboard-model"
import { MqttConfig } from "../mqtt"

interface DashboardComponentViewModel {
    readonly config: MqttConfig
    devices: Device[]
}
class DashboardComponent extends HTMLElement {
    constructor() {
        super()
        this.attachShadow({mode: "open"})
    }
    connectedCallback() {
        store
            .pipe(
                filter(model => !!model.mqttConfig),
                map(toViewModel)
            )
            .subscribe(vm => this.render(vm))

    }
    render(vm: DashboardComponentViewModel) {
        render(template(vm), this.shadowRoot)
    }
}
customElements.define("dashboard-component", DashboardComponent)

function toViewModel(model: DashboardModel) {
    const devices: Device[] = []
    model.devices.forEach((device, name) => {
        devices.push(device)
    })
    devices.sort((l, r) => l.name.toLowerCase().localeCompare(r.name.toLowerCase()))

    const vm: DashboardComponentViewModel = {
        config: model.mqttConfig,
        devices
    }
    return vm
}

function deviceTemplate(device: Device) {
    const formattedTime = device.lastValueReceivedAt.toLocaleTimeString()
    return html`
        <div class="w3-container w3-sans-serif">
            <div class="w3-panel">
                <div class="">
                    <table class="w3-table-all box-table">
                        <caption class="w3-dark-grey">${device.name}</caption>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th class="w3-right">Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr><td>latitude</td><td class="w3-right">${device.location.latitude}</td></tr>
                            <tr><td>longitude</td><td class="w3-right">${device.location.longitude}</td></tr>
                            <tr><td>time</td><td class="w3-right">${formattedTime}</td></tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    `
}
function template(vm: DashboardComponentViewModel)  {
    const devices = vm.devices.map(deviceTemplate)
    const cfg = vm.config
    const connectString = `${cfg.clientId} - ${cfg.host}:${cfg.port}`

    console.log("render", vm)
    return html`
    ${styles}
    <style>
        .box-container {
            display: flex;
            align-items: flex-start;
            justify-content: center;
            flex-wrap: wrap;
        }
        .spring {
            flex-grow: 1;
        }
        .box-table {
            margin: auto;
            width: auto;
        }
        table {
            table-layout: auto;
        }
        th, td {
            width: auto;
        }
    </style>
    <div class="w3-container">
        <h3 class="w3-panel w3-center">
            <span class="w3-monospace">
                <mqtt-connected-icon></mqtt-connected-icon>
                Locations
            </span>
            ${connectString}
        </h3>
    </div>
    <section>
        <div class="box-container">
            ${devices}
        </div>
    </section>
    `
}

