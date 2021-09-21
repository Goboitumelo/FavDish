package com.goboitumelo.apps.favdish.viewmodel

import androidx.lifecycle.*
import com.goboitumelo.apps.favdish.model.database.FavDishRepository
import com.goboitumelo.apps.favdish.model.entities.FavDish
import kotlinx.coroutines.launch

/**
 * The ViewModel's role is to provide data to the UI and survive configuration changes.
 * A ViewModel acts as a communication center between the Repository and the UI.
 */
class FavDishViewModel(private val repository: FavDishRepository) : ViewModel() {


    fun insert(dish: FavDish) = viewModelScope.launch {
        // Call the repository function and pass the details.
        repository.insertFavDishData(dish)
    }
    val allDishesList: LiveData<List<FavDish>> = repository.allDishesList.asLiveData()
    fun update(dish: FavDish) = viewModelScope.launch {
        repository.updateFavDishData(dish)
    }

    // Get the list of favorite dishes that we can populate in the UI.
    val favoriteDishes: LiveData<List<FavDish>> = repository.favoriteDishes.asLiveData()

    /**
     * Launching a new coroutine to delete the data in a non-blocking way.
     */
    fun delete(dish: FavDish) = viewModelScope.launch {
        // Call the repository function and pass the details.
        repository.deleteFavDishData(dish)
    }

    /**
     * A function to get the filtered list of dishes based on the dish type selection.
     */
    fun getFilteredList(value: String): LiveData<List<FavDish>> =
        repository.filteredListDishes(value).asLiveData()
}

class FavDishViewModelFactory(private val repository: FavDishRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavDishViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavDishViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}