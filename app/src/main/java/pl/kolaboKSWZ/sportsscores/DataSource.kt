package pl.kolaboKSWZ.sportsscores

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialMatchList=matchesList(resources)
    private val matchLiveData= MutableLiveData(initialMatchList)

    fun addMatch(match: Match)
    {
        val currentList=matchLiveData.value
        if(currentList==null)
        {
            matchLiveData.postValue(listOf(match))
        }
        else
        {
            val updatedList=currentList.toMutableList()
            updatedList.add(match)
            matchLiveData.postValue(updatedList)
        }
    }

    fun removeMatch(match:Match)
    {
        val currentList=matchLiveData.value
        if(currentList!=null)
        {
            val updatedList=currentList.toMutableList()
            updatedList.remove(match)
            matchLiveData.postValue(updatedList)
        }
    }
    fun getMatchForId(id:Int): Match?
    {
        matchLiveData.value?.let{tasks -> return tasks.firstOrNull{it.matchID==id}}
        return null
    }

    fun getMatchList(): LiveData<List<Match>>
    {
        return matchLiveData
    }


    companion object
    {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources):DataSource
        {
            return synchronized(DataSource::class)
            {
                val newInstance=INSTANCE ?: DataSource(resources)
                INSTANCE=newInstance
                newInstance
            }
        }
    }
}