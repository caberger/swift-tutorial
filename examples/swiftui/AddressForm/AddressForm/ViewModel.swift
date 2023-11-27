import Foundation

class ViewModel: ObservableObject {
    @Published private(set) var model: AddressModel
    
    init(model: AddressModel) {
        self.model = model
    }
    var firstName: String {
        get {
            model.firstName
        }
    }
    var lastName: String {
        model.lastName
    }
    var streetAndNumber: TwoFieldsWithLabels {
        TwoFieldsWithLabels(widthOfFirstField: nil, firstLabel: "Street", firstFieldValue: model.street, widthOfSecondField: 48, secondLabel: "No.", secondFieldValue: model.houseNumber)
    }
    var zipAndCity: TwoFieldsWithLabels {
        TwoFieldsWithLabels(widthOfFirstField: 80, firstLabel: "zip", firstFieldValue: model.zipCode, widthOfSecondField: nil, secondLabel: "city", secondFieldValue: model.city)
    }
    func dataChanged(
        firstName: String? = nil,
        lastName: String? = nil,
        street: String? = nil,
        houseNumber: String? = nil,
        zipCode: String?  = nil,
        city: String? = nil
    ) {
        model.dataChanged(firstName: firstName, lastName: lastName, street: street, houseNumber: houseNumber, zipCode: zipCode, city: city)
    }
    var isValid: Bool {
        model.isValid
    }
}
