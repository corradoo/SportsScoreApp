package pl.kolaboKSWZ.sportsscores

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataSource(resources: Resources,context: Context) {

    private lateinit var database : AppDatabase
    private lateinit var list : ArrayList<Match>
    private var myContext=context.applicationContext
    private var initialMatchList=matchesList(resources)
    private var matchLiveData= MutableLiveData(initialMatchList)

    init {
        insertMatches()
        allMatches()
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

        fun getDataSource(resources: Resources, context: Context):DataSource
        {
            return synchronized(DataSource::class)
            {
                val newInstance=INSTANCE ?: DataSource(resources,context)
                INSTANCE=newInstance
                newInstance
            }
        }
    }

    fun allMatches()
    {
        list = arrayListOf()
        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }
            //System.out.println(database.gameDAO().getAll())
            val currentList = matchLiveData.value
            val updatedList=currentList!!.toMutableList()
            val toDelete=updatedList[0]
            updatedList.remove(toDelete)
            list.addAll(database.matchDAO().getAll())
            //TO-DO avoid starting with one entry
            for (i in list)
            {
                updatedList.add(i)
                matchLiveData.postValue(updatedList)
            }
        }
    }


    //TO-DO: API connection
    //
    fun insertMatches()
    {
        list= arrayListOf()
        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }
            for (i in list)
            {
                System.out.println(i.matchID)
                database.matchDAO().insertAll(i)
            }
        }
    }
}