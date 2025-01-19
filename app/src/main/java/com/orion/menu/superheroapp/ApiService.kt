package com.orion.menu.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/1bca3f253f8d18a8a8e8e65b010fb923/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroesName: String): Response<SuperHeroDataResponse>

    @GET("/api/1bca3f253f8d18a8a8e8e65b010fb923/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId : String): Response<SuperHeroDetailResponse>

}