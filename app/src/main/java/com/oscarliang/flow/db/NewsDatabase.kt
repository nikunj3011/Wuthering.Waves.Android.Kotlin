package com.oscarliang.flow.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oscarliang.flow.models.db.Category
import com.oscarliang.flow.models.db.News
import com.oscarliang.flow.models.db.NewsSearchResult
//import com.oscarliang.flow.models.db.Video

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