package innovatixhub.`in`.vardhan_yadav_seekho.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import innovatixhub.`in`.vardhan_yadav_seekho.AnimeScreens
import innovatixhub.`in`.vardhan_yadav_seekho.data.Data
import innovatixhub.`in`.vardhan_yadav_seekho.domain.AnimeViewModel
import kotlinx.coroutines.launch

@Composable
fun AnimeHomeScreen(navController: NavHostController) {
    val coroutineScope= rememberCoroutineScope()
    val viewModel:AnimeViewModel = hiltViewModel()
    val data=viewModel.animeData.collectAsState().value
    LaunchedEffect(true){
             viewModel.getAnimeData()
    }
    if(data.isEmpty()){

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
           Column {
               CircularProgressIndicator()
               Text("Loading...")
           }
        }

    }
    else{
           Surface(modifier = Modifier.fillMaxSize()) {

               LazyColumn {
                   items(data) {
                       AnimeRowItem(it) { id ->
                           navController.navigate("${AnimeScreens.DETAILS_SCREEN.name}/$id")
                       }
                   }
               }
           }
     }
}
@Composable
fun AnimeRowItem(item: Data, onClick:(Int)->Unit){

    Surface(modifier = Modifier.fillMaxWidth().height(100.dp).padding(top = 10.dp, start = 10.dp).clickable {
        onClick(item.mal_id)
    }, shape = RoundedCornerShape(10.dp)) {
       Row(modifier = Modifier.fillMaxSize()) {
                val imageUrl=item.images.jpg.image_url
               AsyncImage(model = imageUrl, contentDescription = "", contentScale = ContentScale.Fit, modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)))

           Column (modifier = Modifier.fillMaxSize().padding(10.dp)){
               Text(item.title, fontSize = 16.sp , maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f), fontWeight = FontWeight.SemiBold)
               Row(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                           Icon(
                               imageVector = Icons.Default.Star,
                               "rating icon",
                               tint = Color.Yellow,
                               modifier = Modifier.size(12.dp)
                           )
                           Text(item.rating, fontSize = 12.sp)
                       }


               }
               Row(modifier = Modifier.weight(1f)){
                        Text("Total Episodes :${item.episodes}", fontSize = 12.sp)

               }
           }

       }
    }
}
