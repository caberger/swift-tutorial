import Foundation
import CoreLocation
import SwiftUI

let statusMessages : [CLAuthorizationStatus : String] = [
    .notDetermined: "notDetermined",
    .authorizedWhenInUse: "authorizedWhenInUse",
    .authorizedAlways: "authorizedAlways",
    .restricted: "restricted",
    .denied: "denied"
]

class LocationViewModel: NSObject, ObservableObject, CLLocationManagerDelegate {
    @Published private(set) var locationModel = LocationModel()
    @Published private(set) var locationStatus: CLAuthorizationStatus?

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
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if let lastLocation = locations.last {
            let location = LocationData(longitude: lastLocation.coordinate.latitude, latitude: lastLocation.coordinate.longitude)
            locationModel.locationChanged(location)
            Task {
                let place = await computePlace(loc: lastLocation)
                DispatchQueue.main.async {
                    self.locationModel.computed(place: place)
                }
            }
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
