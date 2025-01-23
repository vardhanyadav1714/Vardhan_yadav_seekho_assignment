package innovatixhub.`in`.vardhan_yadav_seekho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import innovatixhub.`in`.vardhan_yadav_seekho.presentation.AnimeHomeScreen
import innovatixhub.`in`.vardhan_yadav_seekho.ui.theme.Vardhan_yadav_seekhoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vardhan_yadav_seekhoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
   Navigation()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Vardhan_yadav_seekhoTheme {
        Greeting("Android")
    }
}