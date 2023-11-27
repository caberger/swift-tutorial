import SwiftUI

/** our single source of truth */
fileprivate let model = AddressModel()

@main
struct AddressFormApp: App {
    
    var viewModel = ViewModel(model: model)
    var body: some Scene {
        WindowGroup {
            AddressFormView(isEditing: true, viewModel: viewModel)
        }
    }
}
