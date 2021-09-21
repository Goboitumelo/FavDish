package com.goboitumelo.apps.favdish.application

import android.app.Application
import com.goboitumelo.apps.favdish.model.database.FavDishRepository
import com.goboitumelo.apps.favdish.model.database.FavDishRoomDatabase

/**
 * A application class where I can define the variable scope to use through out the application.
 */
class FavDishApplication : Application() {


    private val database by lazy { FavDishRoomDatabase.getDatabase(this@FavDishApplication) }

    // A variable for repository.
    val repository by lazy { FavDishRepository(database.favDishDao()) }
}