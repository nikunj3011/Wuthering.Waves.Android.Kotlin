package wutheringwavesguide.ui.weapons

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wutheringwavesguide.models.api.echo.EchoesResponseItem
import wutheringwavesguide.models.api.weapon.WeaponResponseItem
import wutheringwavesguide.repository.HomeRepository

class WeaponViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _weaponsLiveData = MutableLiveData<List<WeaponResponseItem>>()
    val weaponsLiveData: LiveData<List<WeaponResponseItem>> get() = _weaponsLiveData

    init {
        fetchEchoes()
    }
    private fun fetchEchoes() {
        viewModelScope.launch {
            try {
                val newItem = withContext(Dispatchers.IO) {
                    repository.fetchWeapons()
                }
                _weaponsLiveData.postValue(newItem.body()?.toList())
                Log.e("element", newItem.toString())
            } catch (e: Exception) {
                // Handle exceptions, if any
                Log.e("getDetail", "Error fetching details: ${e.message}")
            }
        }
    }

}