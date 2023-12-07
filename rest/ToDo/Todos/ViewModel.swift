import Foundation

class ViewModel: ObservableObject {
    typealias Todo = Model.Todo
    @Published private var model: Model
    
    var todos: [Model.Todo] {
        model.todos
    }
    init(model: Model) {
        self.model = model
    }
    func gehseisofeschundnimmdeneuentodos(_ todos: [Model.Todo]) {
        model.attachTodos(todos: todos)
    }
}
func completedString(_ completed: Bool) -> String {
    completed ? "☑️" : "❌"
}

