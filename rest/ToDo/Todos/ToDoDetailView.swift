import SwiftUI


struct ToDoDetailView: View {
    typealias Todo = Model.Todo
    var todo: Todo
    
    var body: some View{
        VStack {
            Text("\(todo.id)")
            Text(todo.title)
            Text(completedString(todo.completed))
        }
    }
}

struct NavigationView_Previews: PreviewProvider {
    static var model = Model(todos: [Model.Todo(userId: 1, id: 1, title: "test", completed: true)])
    static var viewModel = ViewModel(model: model)
    static var previews: some View {
        ToDoListView(viewModel: viewModel)
    }
}
