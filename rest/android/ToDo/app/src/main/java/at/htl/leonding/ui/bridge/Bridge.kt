package at.htl.leonding.ui.bridge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.platform.ComposeView
import at.htl.leonding.model.ToDoModel
import at.htl.leonding.model.ToDoViewModel
import at.htl.leonding.ui.layout.MainViewSurface

fun fillMainViewContent(
    view: ComposeView,
    viewModel: ToDoViewModel
) {
    view.setContent {
        val model = viewModel.store.subscribeAsState(initial = ToDoModel()).value
        MainViewSurface(model)
    }
}
fun fill(view: ComposeView, surface: @Composable () -> Unit) {
    view.setContent {surface}
}