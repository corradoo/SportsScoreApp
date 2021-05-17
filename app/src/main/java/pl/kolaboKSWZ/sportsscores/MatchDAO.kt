package pl.kolaboKSWZ.sportsscores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface matchDAO {
    @Query("SELECT * FROM Matches ")
    fun getAll(): List<Match>

    @Insert
    fun insertAll(vararg game: Match)
}