package wutheringwavesguide

import androidx.lifecycle.ViewModel
import wutheringwavesguide.repository.UserRepository

class MainViewModel(
    repository: UserRepository
) : ViewModel() {

    val darkModeLiveData = repository.darkModeLiveData

}