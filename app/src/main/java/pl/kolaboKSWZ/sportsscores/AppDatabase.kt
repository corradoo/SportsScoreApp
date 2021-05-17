package pl.kolaboKSWZ.sportsscores

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Match::class)],version=1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDAO():matchDAO

}