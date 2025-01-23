package innovatixhub.`in`.vardhan_yadav_seekho.domain

import innovatixhub.`in`.vardhan_yadav_seekho.data.AnimeData
import innovatixhub.`in`.vardhan_yadav_seekho.data.Data
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val api: AnimeDataApi){

    suspend fun getAnimeData():List<Data>{
        return api.getData().data
    }
    suspend fun getAnimeDetails(id:Int):Data{
        return api.getDetails(id).data
    }

}