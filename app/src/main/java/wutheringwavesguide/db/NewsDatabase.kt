package wutheringwavesguide.db

import androidx.room.Database
import androidx.room.RoomDatabase
import wutheringwavesguide.models.db.Category
import wutheringwavesguide.models.db.News
import wutheringwavesguide.models.db.NewsSearchResult

@Database(
    entities = [News::class, NewsSearchResult::class, Category::class],
//        , Video::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun categoryDao(): CategoryDao

//    abstract fun videosDao(): VideosDao

}