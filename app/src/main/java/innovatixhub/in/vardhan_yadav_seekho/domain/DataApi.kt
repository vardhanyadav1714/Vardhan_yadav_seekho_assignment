package innovatixhub.`in`.vardhan_yadav_seekho.domain

import innovatixhub.`in`.vardhan_yadav_seekho.data.AnimeData
import innovatixhub.`in`.vardhan_yadav_seekho.data.AnimeDetailsData
import innovatixhub.`in`.vardhan_yadav_seekho.data.Data
 import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeDataApi{

    @GET("v4/top/anime")
    suspend fun getData():AnimeData



    @GET("v4/anime/{id}")
    suspend fun getDetails(@Path("id") animeId: Int): AnimeDetailsData
}