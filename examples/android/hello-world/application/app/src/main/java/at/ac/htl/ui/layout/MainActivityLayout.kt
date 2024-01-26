package at.ac.htl.ui.layout

import android.util.Log
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
import at.ac.htl.feature.main.MainViewModel
import at.ac.htl.model.InitialState
import at.ac.htl.model.ObdModel
import at.ac.htl.model.Store
import at.ac.htl.ui.theme.MyFirstAppTheme
import at.ac.htl.util.ObjectMapperFactory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

private val TAG = MainActivityLayout::class.java.canonicalName
@ActivityScoped
class MainActivityLayout {
    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    constructor(viewModel: MainViewModel) {
        this.viewModel = viewModel
    }
    fun setContentViewFor(activity: ComponentActivity) {
        activity.setContent {
            MyFirstAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel)
                }
            }
        }
    }
}
@Composable
fun Greeting(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val state = viewModel.observable
        .doOnError { Log.e(TAG, it.message!! )}
        .subscribeAsState(initial = ObdModel()).value
    Column {
        Text(
            text = state.rpm.toString(),
            modifier = modifier
        )
        Button(onClick = {
            viewModel.setRpm(state.rpm + 10)
            viewModel.routeTo(DetailActivity::class.java)
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
        //Greeting(store)
    }
}