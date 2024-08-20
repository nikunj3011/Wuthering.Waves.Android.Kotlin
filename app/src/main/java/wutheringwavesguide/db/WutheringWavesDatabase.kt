//package wutheringwavesguide.db
//
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import wutheringwavesguide.models.db.Category
//import wutheringwavesguide.models.db.Character
//import wutheringwavesguide.models.db.Echo
//import wutheringwavesguide.models.db.News
//import wutheringwavesguide.models.db.NewsSearchResult
//import wutheringwavesguide.models.db.Weapon
//
//@Database(
//    entities = [Character::class, Echo::class, Weapon::class],
//    version = 1,
//    exportSchema = false
//)
//abstract class WutheringWavesDatabase : RoomDatabase() {
//
//    abstract fun charactersDao(): CharactersDao
//
//    abstract fun echosDao(): EchosDao
//
//    abstract fun weaponsDao(): WeaponsDao
//
//
//}