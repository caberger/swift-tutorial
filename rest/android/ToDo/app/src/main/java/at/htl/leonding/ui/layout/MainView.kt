package at.htl.leonding.ui.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.htl.leonding.model.ToDo
import at.htl.leonding.model.ToDoModel
import at.htl.leonding.ui.theme.ToDoTheme

@Composable
fun MainViewSurface(viewModel: ToDoModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ToDos(viewModel)
    }
}
@Composable
fun ToDos(model: ToDoModel) {
    LazyColumn {
        items(model.toDos.size) { index ->
            ToDoRow(toDo = model.toDos.get(index))
            Divider()
        }
    }
}
@Composable
fun ToDoRow(toDo: ToDo) {
    Row {
        Text(toDo.id.toString() + " ")
        Text(toDo.title)
    }
}
@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    var model = ToDoModel(listOf(
        ToDo(1, 1, "First Todo", false),
        ToDo(2, 2, "NextTodo", true)
    ))
    ToDoTheme {
        ToDos(model)
    }
}


