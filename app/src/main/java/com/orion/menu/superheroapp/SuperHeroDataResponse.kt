package com.orion.menu.superheroapp

import com.google.gson.annotations.SerializedName


data class SuperHeroDataResponse (

   @SerializedName("response") val  response: String,
   @SerializedName("results") val superheroes: List<SuperheroItemResponse>
)

data class SuperheroItemResponse(
   @SerializedName("id") val superheroId: String,
   @SerializedName("name") val name:String,
   @SerializedName("image") val superHeroImage:SuperHeroImageResponse
)


data class SuperHeroImageResponse(
   @SerializedName("url") val url:String
)