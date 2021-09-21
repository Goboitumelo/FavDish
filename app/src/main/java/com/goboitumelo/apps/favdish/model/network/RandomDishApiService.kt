package com.goboitumelo.apps.favdish.model.network

import com.goboitumelo.apps.favdish.model.entities.RandomDish
import com.goboitumelo.apps.favdish.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RandomDishApiService {

    /**
     * Retrofit adapts a Java interface to HTTP calls by using annotations on the declared methods to
     * define how requests are made.
     */
    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL) // Set the API base URL.
        // Add converter factory for serialization and deserialization of objects.
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build() // Create the Retrofit instance using the configured values.
        // Create an implementation of the API endpoints defined by the service interface in our case it is RandomDishAPI.
        .create(RandomDishAPI::class.java)

    fun getRandomDish(): Single<RandomDish.Recipes> {
        return api.getRandomDish(
            Constants.API_KEY_VALUE,
            Constants.LIMIT_LICENSE_VALUE,
            Constants.TAGS_VALUE,
            Constants.NUMBER_VALUE
        )
    }
}