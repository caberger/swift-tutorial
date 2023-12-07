
import Foundation

let todoUrlString = "https://jsonplaceholder.typicode.com/todos"
let todoURL = URL(string: todoUrlString)!

func loadAllTodos() async -> [Model.Todo] {
    var todos = [Model.Todo]()
    if let (data, _) = try? await URLSession.shared.data(from: todoURL) {
        if let loadedTodos = try? JSONDecoder().decode([Model.Todo].self, from: data) {
            todos = loadedTodos
        } else {
            print(":(")
        }
    } else {
        print(":/")
    }
    return todos
}
