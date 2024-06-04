package wutheringwavesguide.ui.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import wutheringwavesguide.models.db.News
import wutheringwavesguide.repository.NewsRepository
import kotlinx.coroutines.launch

class BookmarksViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    val bookmarks: LiveData<List<News>> = repository.getBookmarks()

    fun toggleBookmark(news: News) {
        val current = news.bookmark
        val updated = news.copy(bookmark = !current)
        viewModelScope.launch {
            repository.updateNews(updated)
        }
    }

}