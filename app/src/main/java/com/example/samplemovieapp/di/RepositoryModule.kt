package com.example.samplemovieapp.di

import com.example.samplemovieapp.data.db.remote.MovieService
import com.example.samplemovieapp.data.db.remote.PeopleService
import com.example.samplemovieapp.data.db.remote.TvService
import com.example.samplemovieapp.data.db.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    fun provideMovieRepository(
            movieService: MovieService
    ): MovieRepository {
        return MovieRepositoryImpl(movieService)
    }

    @Provides
    fun provideTvRepository(
            tvService: TvService
    ): TvRepository {
        return TvRepositoryImpl(tvService)
    }

    @Provides
    fun providePeopleRepository(
            peopleService: PeopleService
    ): PeopleRepository {
        return PeopleRepositoryImpl(peopleService)
    }
}