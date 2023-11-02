package at.ac.htl.sensors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.ac.htl.sensors.model.LocationViewModel
import at.ac.htl.sensors.model.Model
import at.ac.htl.sensors.ui.theme.AndroidMqttSensorsTheme

class MainActivity : ComponentActivity() {
    private var locationManager = LocationManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: LocationViewModel by viewModels()

        setContent {
            AndroidMqttSensorsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocationView(viewModel)
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        locationManager.start(this)
    }
    override fun onStop() {
        super.onStop()
        locationManager.stop()
    }
}

@Composable
fun LocationView(viewModel: LocationViewModel) {
    val store = viewModel.store
    var connected: State<Boolean> = store.map { it.isMqttConnected }.subscribeAsState(initial = false)
    val locationData: State<Model.LocationData> = store.map { it.locationData }.subscribeAsState(Model.LocationData())
    val txt = if (locationData.value.valid) "(${locationData.value.latitude}, ${locationData.value.longitude})" else "..."
    var connectionId = R.drawable.disconnected
    var color = Color.Red
    if (connected.value) {
        color = Color.Transparent
        connectionId = R.drawable.connected
    }
    val connIconId = if (connected.value) R.drawable.connected else R.drawable.disconnected
    val padding = 16.dp
    Column(
        Modifier
            .padding(padding)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Icon(
            painter = painterResource(connIconId),
            contentDescription = stringResource(id = R.string.connection_status),
            modifier = Modifier.size(size = 32.dp).background(color)
        )
        Spacer(Modifier.size(padding))
        Text(
            text = txt
        )
    }
}
@Preview(showBackground = true)
@Composable
fun LocationViewPreview() {
    val viewModel = LocationViewModel()

    AndroidMqttSensorsTheme {
        LocationView(viewModel)
    }
}
