import SwiftUI

fileprivate let locationManager = LocationViewModel()

@main
struct ThingWorksApp: App {
    var body: some Scene {
        WindowGroup {
            LocationView()
        }
    }
}
