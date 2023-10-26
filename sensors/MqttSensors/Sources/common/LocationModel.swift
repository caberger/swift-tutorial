import CoreLocation

typealias Coordinate = Double

struct LocationData {
    var longitude: Coordinate
    var latitude: Coordinate
}
struct AddressModel {
    var name = ""
    var street1 = ""
    var street2 = ""
    var city = ""
    
    mutating func from(place: CLPlacemark) {
        if let theName = place.name {
            name = theName
        }
        if let thoroughfare = place.thoroughfare {
            street1 = thoroughfare
        }
        if let subThoroughfare = place.subThoroughfare {
            street2 = subThoroughfare
        }
        if let locality = place.locality {
            city = locality
        }
    }
}

struct LocationModel {
    private(set) var locationData: LocationData?
    private(set) var address = AddressModel()
    private(set) var status = ""
    
    mutating func locationChanged(_ data: LocationData) {
        locationData = data
    }
    mutating func computed(place computedPlace: CLPlacemark?) {
        address = AddressModel()
        if let place = computedPlace {
            address.from(place: place)
        }
    }
    mutating func changed(status: String) {
        self.status = status
    }
}
			
