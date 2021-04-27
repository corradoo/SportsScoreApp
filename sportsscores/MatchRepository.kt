
package pl.kolaboKSWZ.sportsscores

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MatchRepository(private val matchDao: MatchDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allMatches: ArrayList<Match> = matchDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(match: Match) {
        matchDao.insert(match)
    }
    fun returnData() : List<Match>
    {
        return allMatches.toList()
    }
}