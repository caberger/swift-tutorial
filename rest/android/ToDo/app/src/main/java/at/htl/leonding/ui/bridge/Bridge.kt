package at.htl.leonding.ui.bridge

import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.platform.ComposeView
import at.htl.leonding.model.Store
import at.htl.leonding.model.ToDoModel
import at.htl.leonding.ui.layout.MainViewSurface

fun fillMainViewContent(
    view: ComposeView,
    store: Store
) {
    view.setContent {
        val model = store.toDoModel.subscribeAsState(initial = ToDoModel()).value
        MainViewSurface(model)
    }
}
