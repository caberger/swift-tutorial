import Foundation

class ViewModel: ObservableObject {
    private var model: AddressModel
    
    init(model: AddressModel) {
        self.model = model
    }
    var firstName: String {
        get {
            model.firstName
        }
        set {
            print("setting first name \(newValue)")
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
}
