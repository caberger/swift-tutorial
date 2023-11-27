import SwiftUI

struct AddressFormView: View {
    @State var isEditing: Bool
    @ObservedObject var viewModel : ViewModel
    var body: some View {
        VStack {
            Form {
                let firstNameField = AddressFormLine(isEditing: isEditing, label: "First Name", text: viewModel.firstName)
                firstNameField
                AddressFormLine(isEditing: isEditing, label: "Last Name", text: viewModel.lastName)
                if !isEditing {
                    HStack {
                        Text("Address:")
                        Spacer()
                    }.padding(.vertical)
                }
                TwoFieldsWithLabelsView(isEditing: isEditing, twoFields: viewModel.streetAndNumber)
                TwoFieldsWithLabelsView(isEditing: isEditing, twoFields: viewModel.zipAndCity)
                if isEditing {
                    Spacer()
                    Button {
                        print("button pressed -->\(firstNameField.text)<--")
                    } label: {
                        Text("Save")
                    }
                }
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
