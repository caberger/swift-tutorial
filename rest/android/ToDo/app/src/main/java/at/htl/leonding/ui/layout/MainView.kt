package at.htl.leonding.ui.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.htl.leonding.model.ToDoModel
import at.htl.leonding.ui.theme.ToDoTheme

@Composable
fun MainViewSurface(viewModel: ToDoModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting(viewModel)
    }
}
@Composable
fun Greeting(model: ToDoModel, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${model.toDos().size}!",
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoTheme {
        Greeting(ToDoModel())
    }
}


