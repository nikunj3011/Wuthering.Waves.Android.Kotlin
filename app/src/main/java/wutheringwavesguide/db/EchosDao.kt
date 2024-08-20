package wutheringwavesguide.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import wutheringwavesguide.models.db.Echo

@Dao
interface EchosDao {

    @Upsert()
    suspend fun upsertEchoes(categories: List<Echo>)

    @Query("SELECT * FROM echos")
    suspend fun findEchos(): List<Echo>

    @Query("SELECT * FROM echos")
    fun getEchos(): LiveData<List<Echo>>

    @Query("SELECT * FROM echos WHERE name = :name")
    fun getSelectedEcho(name: String): LiveData<Echo>

//    @Query("UPDATE echos SET name = 1 WHERE id = :Echo")
//    suspend fun setSelectedCategory(Echo: String)
//
//    @Query("UPDATE echos SET isSelected = 0 WHERE id != :Echo")
//    suspend fun clearSelectedCategory(Echo: String)

}