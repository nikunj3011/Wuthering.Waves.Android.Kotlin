package wutheringwavesguide.ui.homedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wutheringwavesguide.models.api.echo.EchoesResponseItem
import wutheringwavesguide.repository.HomeRepository

class HomeDataViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _echoesLiveData = MutableLiveData<List<EchoesResponseItem>>()
    var _echoes = listOf<EchoesResponseItem>()
    val echoesLiveData: LiveData<List<EchoesResponseItem>> get() = _echoesLiveData

    init {
        fetchEchoes()
    }
    private fun fetchEchoes() {
        viewModelScope.launch {
            try {
                val newItem = withContext(Dispatchers.IO) {
                    repository.fetchEchoes()
                }
                _echoesLiveData.postValue(newItem.body()?.toList())
                _echoes = newItem.body()!!.toList()
                Log.e("element", newItem.toString())
            } catch (e: Exception) {
                // Handle exceptions, if any
                Log.e("getDetail", "Error fetching details: ${e.message}")
            }
        }
    }

}