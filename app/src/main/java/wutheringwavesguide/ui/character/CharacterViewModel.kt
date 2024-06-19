package wutheringwavesguide.ui.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wutheringwavesguide.models.api.character.CharacterResponse
import wutheringwavesguide.models.api.character.CharacterResponseItem
import wutheringwavesguide.models.api.echo.EchoesResponseItem
import wutheringwavesguide.repository.HomeRepository

class CharacterViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _charactersLiveData = MutableLiveData<List<CharacterResponseItem>>()
    val charactersLiveData: LiveData<List<CharacterResponseItem>> get() = _charactersLiveData

    init {
        fetchEchoes()
    }
    private fun fetchEchoes() {
        viewModelScope.launch {
            try {
                val newItem = withContext(Dispatchers.IO) {
                    repository.fetchCharacters()
                }
                _charactersLiveData.postValue(newItem.body()?.toList())
                Log.e("element", newItem.toString())
            } catch (e: Exception) {
                // Handle exceptions, if any
                Log.e("getDetail", "Error fetching details: ${e.message}")
            }
        }
    }

}