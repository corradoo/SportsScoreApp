package pl.kolaboKSWZ.sportsscores

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface matchDAO {
    //@Query("SELECT * FROM Matches WHERE date LIKE '2021-05-01%'")
    @Query("SELECT * FROM Matches WHERE date LIKE :date")
    fun getAll(date: String): List<Match>

    @Query("SELECT * FROM Matches WHERE seasonID = 3260 AND date LIKE :date")
    fun getEng(date: String): List<Match>

    @Query("SELECT * FROM Matches WHERE seasonID = 3218 AND date LIKE :date")
    fun getGer(date: String): List<Match>

    @Query("SELECT * FROM Matches WHERE seasonID = 3241 AND date LIKE :date")
    fun getIta(date: String): List<Match>

    @Query("SELECT * FROM Matches WHERE seasonID = 3229 AND date LIKE :date")
    fun getSpa(date: String): List<Match>

    @Insert
    fun insertAll(vararg game: Match)
}