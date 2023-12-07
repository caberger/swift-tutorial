import SwiftUI

struct ToDoListView: View {
    @ObservedObject var viewModel: ViewModel
    var body: some View {
        NavigationStack {
            List(viewModel.todos) { todo in
                NavigationLink(value: todo) {
                    TableLine(id: todo.id, text: todo.title, completed: todo.completed)
                }
            }
            .navigationDestination(for: Model.Todo.self) { todo in
                ToDoDetailView(todo: todo)
            }
            .padding()
            .task {
                let todos = await loadAllTodos()
                viewModel.gehseisofeschundnimmdeneuentodos(todos)
            }
        }
    }
}
struct TableLine: View {
    var id: Int
    var text: String
    var completed: Bool
    
    var body: some View {
        HStack {
            Text("id: \(id)")
            Text(text)
            Spacer()
            Text(completedString(completed))
        }
    }
}
struct ContentView_Previews: PreviewProvider {
    static var model = Model()
    static var viewModel = ViewModel(model: model)
    static var previews: some View {
        ToDoListView(viewModel: viewModel)
    }
}
