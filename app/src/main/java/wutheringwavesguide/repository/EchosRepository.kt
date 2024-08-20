package wutheringwavesguide.repository

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import wutheringwavesguide.db.CategoryDao
import wutheringwavesguide.db.NewsDatabase
import wutheringwavesguide.models.db.Category
import wutheringwavesguide.models.db.Echo
import wutheringwavesguide.util.NetworkBoundResource
import wutheringwavesguide.util.Resource

interface EchosRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Echo>>>

    suspend fun getMovie(id: Int): Flow<Resource<Echo>>
}