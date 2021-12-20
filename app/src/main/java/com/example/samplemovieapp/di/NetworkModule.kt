package com.example.samplemovieapp.di

import com.example.samplemovieapp.BuildConfig
import com.example.samplemovieapp.data.db.remote.MovieService
import com.example.samplemovieapp.data.db.remote.PeopleService
import com.example.samplemovieapp.data.db.remote.TheMovieDatabaseAPI
import com.example.samplemovieapp.data.db.remote.TvService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
                .Builder()
                .addInterceptor(RequestInterceptor())
                .build()
    }

    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(TheMovieDatabaseAPI.BASE_API_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
            retrofit.create(MovieService::class.java)

    @Provides
    fun provideTvService(retrofit: Retrofit): TvService =
            retrofit.create(TvService::class.java)

    @Provides
    fun providePeopleService(retrofit: Retrofit): PeopleService =
            retrofit.create(PeopleService::class.java)

    internal class RequestInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val oldRequest = chain.request()
            val url = oldRequest.url.newBuilder()
                    .addQueryParameter("language", "en-US")
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build()
            val newRequest = oldRequest.newBuilder().url(url).build()
            return chain.proceed(newRequest)
        }
    }
}

