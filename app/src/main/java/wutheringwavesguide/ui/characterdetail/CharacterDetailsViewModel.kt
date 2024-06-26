package wutheringwavesguide.ui.characterdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wutheringwavesguide.models.api.characterdetails.CharacterDetailsResponseItem
import wutheringwavesguide.repository.HomeRepository

class CharacterDetailsViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _characterDetailsLiveData = MutableLiveData<List<CharacterDetailsResponseItem>>()
    var _characterDetails = listOf<CharacterDetailsResponseItem>()
    val characterDetailsLiveData: LiveData<List<CharacterDetailsResponseItem>> get() = _characterDetailsLiveData

    init {
        fetchCharacterDetails()
    }
    private fun fetchCharacterDetails() {
        viewModelScope.launch {
            try {
                val newItem = withContext(Dispatchers.IO) {
                    repository.fetchCharactersDetails()
                }
                _characterDetailsLiveData.postValue(newItem.body()?.toList())
                _characterDetails = newItem.body()!!.toList()
                Log.e("element", newItem.toString())
            } catch (e: Exception) {
                // Handle exceptions, if any
                Log.e("getDetail", "Error fetching details: ${e.message}")
            }
        }
    }

}