package wutheringwavesguide.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Weapon(
    @PrimaryKey
    val id: String,
    val name: String,
    val isSelected: Boolean = false
)