package wutheringwavesguide.ui.newsdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import wutheringwavesguide.models.db.News
import wutheringwavesguide.repository.NewsRepository
import wutheringwavesguide.util.AbsentLiveData
import wutheringwavesguide.util.NEWS_MORE_CONTENT_COUNT
import wutheringwavesguide.util.Resource
import kotlinx.coroutines.launch

class NewsDetailViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _id: MutableLiveData<String?> = MutableLiveData()
    val id: LiveData<String?>
        get() = _id

    val news: LiveData<News> = _id.switchMap { id ->
        if (id.isNullOrBlank()) {
            AbsentLiveData.create()
        } else {
            repository.getNewsById(id)
        }
    }

    val moreNews: LiveData<Resource<List<News>>> = news.switchMap { news ->
        if (news.source == null || news.source.id.isBlank()) {
            AbsentLiveData.create()
        } else {
            repository.getNewsBySource(news.source.id, NEWS_MORE_CONTENT_COUNT)
        }
    }

    fun setNewsId(id: String?) {
        if (_id.value != id) {
            _id.value = id
        }
    }

    fun retry() {
        _id.value?.let {
            _id.value = it
        }
    }

    fun toggleBookmark(news: News) {
        val current = news.bookmark
        val updated = news.copy(bookmark = !current)
        viewModelScope.launch {
            repository.updateNews(updated)
        }
    }

}
