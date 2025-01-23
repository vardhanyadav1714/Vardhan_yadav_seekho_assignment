package innovatixhub.`in`.vardhan_yadav_seekho.presentation

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import innovatixhub.`in`.vardhan_yadav_seekho.data.AnimeData
import innovatixhub.`in`.vardhan_yadav_seekho.data.Genre
import innovatixhub.`in`.vardhan_yadav_seekho.domain.AnimeViewModel
import kotlinx.coroutines.withContext

@Composable
fun AnimeDetailsScreen(id: Int, navController: NavHostController){
     val viewModel:AnimeViewModel = hiltViewModel()
    val data=viewModel.animeDetails.collectAsState().value

    LaunchedEffect(id) {
        viewModel.getAnimeDetails(id)

    }

     val youtube_id= data?.trailer?.youtube_id ?: ""
     val imagePoster= data?.trailer?.images?.large_image_url ?:""
      val title=data?.title ?: ""
    val synopsis=data?.synopsis ?: ""
    val genre=data?.genres  ?: emptyList()
    val episodes=data?.episodes ?: 0
    val lifecycleOwner= LocalLifecycleOwner.current
     AnimeUI(youtube_id,imagePoster,title,synopsis,genre,episodes,lifecycleOwner)

}

@Composable
fun AnimeUI(youtube_id:String,imagePoster: String, title: String, synopsis: String, genre: List<Genre>, episodes: Int,lifecycleOwner: LifecycleOwner) {
     val scrollstate= rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollstate)) {

            Column {
                if(!youtube_id.isNullOrEmpty()) {
                    AndroidView(
                        factory = { context ->
                            YouTubePlayerView(context=context).apply {
                                lifecycleOwner.lifecycle.addObserver(this)
                                addYouTubePlayerListener(object:AbstractYouTubePlayerListener(){
                                    override fun onReady(youTubePlayer: YouTubePlayer) {
                                        youTubePlayer.loadVideo(youtube_id,0f)
                                    }
                                })
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )

                }
                if(youtube_id.isNullOrEmpty()) {
                    AsyncImage(
                        model = imagePoster,
                        "imageVector",
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(title, fontSize = 20.sp, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(20.dp))
                Text(synopsis, fontSize = 14.sp, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(20.dp))

                Text("Genre :", fontSize = 14.sp)
                LazyRow {
                    items(genre) {
                        GenreCard(it)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text("Main Cast: No Information Available", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(20.dp))

                Text("Total Episodes : ${episodes.toString()}", fontSize = 14.sp, modifier = Modifier.padding(start = 10.dp))

            }
        }


}

@Composable
fun GenreCard(genre:Genre){
    Surface(modifier = Modifier.wrapContentWidth().wrapContentSize().padding(start = 5.dp), shape = RoundedCornerShape(10.dp), color = Color.Blue) {
        Text(genre.name, fontSize = 14.sp, modifier = Modifier.padding(2.dp), color = Color.LightGray)
    }
}
