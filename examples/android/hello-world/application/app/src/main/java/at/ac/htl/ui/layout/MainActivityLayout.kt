package at.ac.htl.ui.layout

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import at.ac.htl.DetailActivity
import at.ac.htl.model.InitialState
import at.ac.htl.model.Store
import at.ac.htl.ui.theme.MyFirstAppTheme
import at.ac.htl.util.ObjectMapperFactory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainActivityLayout {
    @Inject
    lateinit var store: Store

    @Inject
    constructor(store: Store) {
        this.store = store
    }
    fun setContentViewFor(activity: ComponentActivity) {
        activity.setContent {
            MyFirstAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(store)
                }
            }
        }
    }
}
@Composable
fun Greeting(store: Store, modifier: Modifier = Modifier) {
    val state = store.subject.subscribeAsState(initial = InitialState())
    Column {
        Text(
            text = state.value.greeting,
            modifier = modifier
        )
        TextField(value = state.value.greeting, onValueChange = {
            store.produce{ text -> text.greeting = it }
        })
        Button(onClick = {
            store.produce { it.activity = DetailActivity::class.java }
        }) {
            Text("Detail...")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val model = InitialState();
    model.greeting = "hello world"
    val store = Store(ObjectMapperFactory(), model)
    MyFirstAppTheme {
        Greeting(store)
    }
}