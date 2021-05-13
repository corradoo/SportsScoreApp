package pl.kolaboKSWZ.sportsscores

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MatchDAO {


    @Query("SELECT * FROM Matches")
    fun getAlphabetizedWords() : ArrayList<Match>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(match:Match)

    @Query("DELETE FROM Matches")
    suspend fun deleteAll()
}