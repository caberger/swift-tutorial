import SwiftUI

struct AddressFormView: View {
    @State var isEditing: Bool
    @ObservedObject var viewModel : ViewModel
    var body: some View {
        VStack {
            Text(isEditing ? "Address" : "Summary")
                .font(.title)
                .fontWeight(.bold)
            Form {
                AddressFormLine(isEditing: isEditing, label: "First Name", text: viewModel.firstName) {
                    viewModel.dataChanged(firstName: $0)
                }
                AddressFormLine(isEditing: isEditing, label: "Last Name", text: viewModel.lastName) {
                    viewModel.dataChanged(lastName: $0)
                }
                if !isEditing {
                    HStack {
                        Text("Address:")
                        Spacer()
                    }.padding(.vertical)
                }
                TwoFieldsWithLabelsView(isEditing: isEditing, twoFields: viewModel.streetAndNumber) { street, no in
                    viewModel.dataChanged(street: street, houseNumber: no)

                }
                TwoFieldsWithLabelsView(isEditing: isEditing, twoFields: viewModel.zipAndCity) { zip, city in
                    viewModel.dataChanged(zipCode: zip, city: city)
                }
                Spacer()
                Button {
                    isEditing.toggle()
                } label: {
                    Text(isEditing ? "Save" : "Back")
                }.disabled(!viewModel.isValid)
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
