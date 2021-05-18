package pl.kolaboKSWZ.sportsscores

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MatchesListViewModel(val dataSource:DataSource) : ViewModel()
{

    val matchesLiveData=dataSource.getMatchList()

}

class MatchesListViewModelFactory(private val context: Context) : ViewModelProvider.Factory
{
    override fun <T: ViewModel> create(modelClass:Class<T>): T{
        if(modelClass.isAssignableFrom(MatchesListViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return MatchesListViewModel(dataSource=DataSource.getDataSource(context.resources,context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}