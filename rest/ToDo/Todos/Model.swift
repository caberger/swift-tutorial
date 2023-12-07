struct Model {
    struct Todo: Codable, Identifiable, Hashable {
        var userId: Int
        var id: Int
        var title: String
        var completed: Bool
    }    
    var todos = [Todo]()
    
    mutating func attachTodos(todos: [Todo]) {
        self.todos = todos
    }
}
