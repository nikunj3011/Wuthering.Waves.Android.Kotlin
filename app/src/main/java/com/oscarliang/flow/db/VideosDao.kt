//package com.oscarliang.flow.db
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Query
//import androidx.room.Update
//import com.oscarliang.flow.models.db.Video
//
//@Dao
//interface VideosDao {
//
//    @Query("SELECT * FROM videos WHERE idLink in (:newsIds)")
//    fun getVideosById(newsIds: List<String>): LiveData<List<Video>>
//
//    @Query("SELECT * FROM videos WHERE id = :id")
//    fun getVideosById(id: String): LiveData<Video>
//
//    @Query("SELECT * FROM videos")
//    fun getVideos(): LiveData<List<Video>>
//
//    @Update
//    suspend fun updateVideos(news: Video)
//
//}