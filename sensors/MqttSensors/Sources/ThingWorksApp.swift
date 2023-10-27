import SwiftUI

fileprivate let model = LocationModel()

@main
struct ThingWorksApp: App {
    var locationViewModel = LocationViewModel()
    var publisher = MqttLocationPublisher()

    init() {
        locationViewModel.locationModel = model
    }
    var body: some Scene {
        WindowGroup {
            LocationView(viewModel: locationViewModel)
                .task {
                    let (ok, message) = await publisher.connect()
                    if ok {
                        locationViewModel.locationPublisher = publisher
                    } else {
                        print("failed to connect to publisher, \(message!)")
                    }
                }
        }
    }
}
