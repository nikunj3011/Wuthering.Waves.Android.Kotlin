package wutheringwavesguide.ui.settings

import androidx.lifecycle.ViewModel
import wutheringwavesguide.repository.DarkMode
import wutheringwavesguide.repository.UserRepository

class SettingsViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val darkModeLiveData = repository.darkModeLiveData

    fun updateDarkMode(darkMode: DarkMode) {
        repository.updateDarkMode(darkMode)
    }

}