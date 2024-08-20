package wutheringwavesguide

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wutheringwavesguide.models.api.characterdetail.CharacterDetailResponse
import wutheringwavesguide.models.api.characterdetails.CharacterDetailsResponseItem
import wutheringwavesguide.models.api.echo.EchoesResponseItem
import wutheringwavesguide.repository.HomeRepository

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _characterDetailsLiveData = MutableLiveData<List<CharacterDetailResponse>>()
    val characterDetailsLiveData: LiveData<List<CharacterDetailResponse>> get() = _characterDetailsLiveData

    init {
        fetchCharacters()
    }
    private fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val newItem = withContext(Dispatchers.IO) {
                    repository.fetchCharactersDetails()
                }
                _characterDetailsLiveData.postValue(newItem.body()?.toList())
            } catch (e: Exception) {
                var c = 0
                // Handle exceptions, if any
            }
        }
    }
}