package at.htl.leonding.ui.layout

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import at.htl.leonding.model.Store
import at.htl.leonding.model.ToDo
import at.htl.leonding.model.ToDoModel
import at.htl.leonding.ui.theme.ToDoTheme
import javax.inject.Inject
import javax.inject.Singleton

/** Our View implemented in kotlin.
 * Application Logic is separated from this view.
 * Our State is delivered exclusively from our {@link Store}, which we subscribe here.
 */
@Singleton
class MainView {
    @Inject
    lateinit var store: Store

    @Inject
    constructor() {
    }
    fun setContentOfActivity(activity: ComponentActivity) {
        val view = ComposeView(activity)
        view.setContent {
            val viewModel = store.toDoModel.subscribeAsState(initial = ToDoModel()).value
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ToDos(viewModel)
            }
        }
        activity.setContentView(view)
    }
}
@Composable
fun ToDos(model: ToDoModel) {
    LazyColumn {
        items(model.toDos.size) { index ->
            ToDoRow(toDo = model.toDos[index])
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
        ToDo(2, 2, "Second Todo", true)
    ))
    ToDoTheme {
        ToDos(model)
    }
}


