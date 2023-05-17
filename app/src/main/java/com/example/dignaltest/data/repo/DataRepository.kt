package com.example.dignaltest.data.repo
import com.example.dignaltest.domain.MovieModel

interface DataRepository {

    suspend fun retrieveApiData() :Result<List<MovieModel>>

}