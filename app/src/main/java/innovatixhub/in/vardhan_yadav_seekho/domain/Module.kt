package innovatixhub.`in`.vardhan_yadav_seekho.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    private const val BASE_URL="https://api.jikan.moe/"
    @Provides
    @Singleton
    fun provideAnimeDataApi(): AnimeDataApi{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(AnimeDataApi::class.java)
    }
}