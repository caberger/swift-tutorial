import Foundation
import CoreLocation
import SwiftUI

fileprivate let MIN_PLACE_RECOMPUTE_INTERVAL_IN_SECONDS = 5.0
let statusMessages : [CLAuthorizationStatus : String] = [
    .notDetermined: "notDetermined",
    .authorizedWhenInUse: "authorizedWhenInUse",
    .authorizedAlways: "authorizedAlways",
    .restricted: "restricted",
    .denied: "denied"
]

class LocationViewModel: NSObject, ObservableObject, CLLocationManagerDelegate {
    @Published var locationModel: LocationModel!
    var lastTimePlaceMarkWasComputed = Date.now - MIN_PLACE_RECOMPUTE_INTERVAL_IN_SECONDS
    var locationPublisher: MqttLocationPublisher?

    private var locationManager = CLLocationManager()
    private let geoCoder = CLGeocoder()

    var locationData: LocationData? {
        locationModel.locationData
    }
    var address: AddressModel {
        locationModel.address
    }
    var status: String {
        locationModel.status
    }
    override init() {
        super.init()
        self.locationModel = locationModel
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.requestWhenInUseAuthorization()
        locationManager.startUpdatingLocation()
    }
    private func computePlace(loc: CLLocation) async -> CLPlacemark? {
        var place: CLPlacemark?
        if let revs = try? await geoCoder.reverseGeocodeLocation(loc) {
            place = revs[0]
        }
        return place
    }
    private func changed(location loc: CLLocation) {
        let location = LocationData(longitude: loc.coordinate.latitude, latitude: loc.coordinate.longitude)
        locationModel.locationChanged(location)
        Task {
            let elapsed = Date.now.timeIntervalSince(lastTimePlaceMarkWasComputed)
            if (elapsed > MIN_PLACE_RECOMPUTE_INTERVAL_IN_SECONDS) {
                let place = await computePlace(loc: loc)
                lastTimePlaceMarkWasComputed = Date.now
                DispatchQueue.main.async {
                    self.locationModel.computed(place: place)
                }
            }
            if let publisher = self.locationPublisher {
                do {
                    try await publisher.publish(location: location)
                } catch {
                    print("failed to publish: \(error.localizedDescription)")
                }
            }
        }
    }
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if let lastLocation = locations.last {
            let location = LocationData(longitude: lastLocation.coordinate.latitude, latitude: lastLocation.coordinate.longitude)
            changed(location: lastLocation)
        }
    }
    func locationManagerDidChangeAuthorization(_ manager: CLLocationManager) {
        var status = "unknown"
        if let msg = statusMessages[manager.authorizationStatus] {
            status = msg
        } else {
            status = "impl error"
        }
        locationModel.changed(status: status)
    }
}
