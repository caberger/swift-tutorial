import SwiftUI

fileprivate let model = Model()

@main
struct TodosApp: App {
    var viewModel = ViewModel(model: model)
    
    var body: some Scene {
        WindowGroup {
            ToDoListView(viewModel: viewModel)
            //NavigationView(viewModel: viewModel)
        }
    }
}
