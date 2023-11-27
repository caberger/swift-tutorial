import SwiftUI

struct AddressFormView: View {
    @State var isEditing: Bool
    @State var isValid = false
    @ObservedObject var viewModel : ViewModel
    var body: some View {
        VStack {
            Form {
                let firstNameField = AddressFormLine(isEditing: isEditing, label: "First Name", text: viewModel.firstName) {
                    isValid = viewModel.dataChanged(firstName: $0)
                }
                firstNameField
                AddressFormLine(isEditing: isEditing, label: "Last Name", text: viewModel.lastName) {
                    isValid = viewModel.dataChanged(lastName: $0)
                }
                if !isEditing {
                    HStack {
                        Text("Address:")
                        Spacer()
                    }.padding(.vertical)
                }

                TwoFieldsWithLabelsView(isEditing: isEditing, twoFields: viewModel.streetAndNumber) { street, no in
                    isValid = viewModel.dataChanged(street: street, houseNumber: no)

                }
                TwoFieldsWithLabelsView(isEditing: isEditing, twoFields: viewModel.zipAndCity) { zip, city in
                    isValid = viewModel.dataChanged(zipCode: zip, city: city)
                }
                Spacer()
                Button {
                    isEditing.toggle()
                } label: {
                    Text(isEditing ? "Save" : "Back")
                }.disabled(!isValid)
            }
        }
        .padding()
    }
}

struct AddressFormDisplaying_Previews: PreviewProvider {
    @State static var isEditing = false
    static var model = AddressModel()
    static var viewModel = ViewModel(model: model)
    static var previews: some View {
        AddressFormView(isEditing: isEditing, viewModel: viewModel)
            .padding()
    }
}

struct AddressFormEditing_Previews: PreviewProvider {
    @State static var isEditing = true
    static var model = AddressModel()
    static var viewModel = ViewModel(model: model)

    static var previews: some View {
        AddressFormView(isEditing: isEditing, viewModel: viewModel)
            .padding()
    }
}
