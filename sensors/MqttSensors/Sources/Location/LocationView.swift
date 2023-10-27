import SwiftUI

struct LocationView: View {
    @ObservedObject var viewModel: LocationViewModel
    
    var longitude: String {
        viewModel.locationData != nil ? String(viewModel.locationData!.longitude) : ""
    }
    var latitude: String {
        viewModel.locationData != nil ? String(viewModel.locationData!.latitude) : ""
    }
    var addr: AddressModel {
        viewModel.address
    }
    var body: some View {
        VStack {
            Text(viewModel.status)
                .fontWeight(.light)
                .padding(.bottom)
            VStack {
                Text(addr.name)
                HStack {
                    Text(addr.street1)
                    Text(addr.street2)
                }
                Text(addr.city)
            }
            VStack {
                HStack {
                    Text("(\(latitude), \(longitude))")
                }
            }
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static let locationModel = LocationModel()
    static let locationViewModel = LocationViewModel()
    static var previews: some View {
        locationViewModel.locationModel = locationModel
        return LocationView(viewModel: locationViewModel)
    }
}
