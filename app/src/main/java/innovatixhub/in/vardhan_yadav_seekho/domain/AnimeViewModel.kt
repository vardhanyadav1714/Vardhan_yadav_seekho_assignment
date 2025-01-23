package innovatixhub.`in`.vardhan_yadav_seekho.domain

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import innovatixhub.`in`.vardhan_yadav_seekho.data.Data
import innovatixhub.`in`.vardhan_yadav_seekho.data.Images
import innovatixhub.`in`.vardhan_yadav_seekho.data.ImagesX
import innovatixhub.`in`.vardhan_yadav_seekho.data.Jpg
import innovatixhub.`in`.vardhan_yadav_seekho.data.Trailer
import innovatixhub.`in`.vardhan_yadav_seekho.data.Webp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(private val repository: AnimeRepository):ViewModel(){
    private val _animeData=MutableStateFlow<List<Data>>(emptyList())
    val animeData:StateFlow<List<Data>> = _animeData

    private val _animeDetails = MutableStateFlow<Data?>(null)
    val animeDetails: StateFlow<Data?> = _animeDetails


    suspend fun getAnimeData() {
        try {
            val data=repository.getAnimeData()
            Log.d("data is","$data")
            _animeData.value=data

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    suspend fun getAnimeDetails(id:Int){
       try {

           val data=repository.getAnimeDetails(id)
           Log.d("details data","data is ${data}")
           _animeDetails.value=data

       }catch (e:Exception){
           e.printStackTrace()
       }

    }

}