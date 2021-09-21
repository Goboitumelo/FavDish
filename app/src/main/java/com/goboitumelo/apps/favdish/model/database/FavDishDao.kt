package com.goboitumelo.apps.favdish.model.database

import androidx.room.*
import com.goboitumelo.apps.favdish.model.entities.FavDish
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDishDao {

    /**
     * All queries are executed on a separate thread. They cannot be executed from Main Thread or it will cause an crash.
     *
     * Room has Kotlin coroutines support.
     * This allows your queries to be annotated with the suspend modifier and then called from a coroutine
     * or from another suspension function.
     */
    @Insert
    suspend fun insertFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE ORDER BY ID")
    fun getAllDishesList(): Flow<List<FavDish>>

    @Update
    suspend fun updateFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE WHERE favorite_dish = 1")
    fun getFavoriteDishesList(): Flow<List<FavDish>>

    @Delete
    suspend fun deleteFavDishDetails(favDish: FavDish)

    @Query("SELECT * FROM FAV_DISHES_TABLE WHERE type = :filterType")
    fun getFilteredDishesList(filterType: String): Flow<List<FavDish>>

    @Query("SELECT * FROM FAV_DISHES_TABLE ORDER BY ID")
    fun getDishesList(): Flow<List<FavDish>>
}