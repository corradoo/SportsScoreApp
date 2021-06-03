package pl.kolaboKSWZ.sportsscores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface matchDAO {
    @Query("SELECT * FROM Matches WHERE date LIKE '2021-05-01%'")
    fun getAll(): List<Match>

    @Query("SELECT * FROM Matches WHERE seasonID = 3260")
    fun getEng(): List<Match>

    @Query("SELECT * FROM Matches WHERE seasonID = 3218 ")
    fun getGer(): List<Match>

    @Query("SELECT * FROM Matches WHERE seasonID = 3241")
    fun getIta(): List<Match>

    @Query("SELECT * FROM Matches WHERE seasonID = 3229")
    fun getSpa(): List<Match>

    @Insert
    fun insertAll(vararg game: Match)
}