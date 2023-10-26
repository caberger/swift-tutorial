import SwiftUI

fileprivate let locationManager = LocationViewModel()

@main
struct ThingWorksApp: App {
    var contentView: LocationView
    init() {
        contentView = LocationView()
    }
    var body: some Scene {
        WindowGroup {
            contentView
        }
    }
}
