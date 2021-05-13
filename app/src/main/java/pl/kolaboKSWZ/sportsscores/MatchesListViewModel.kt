package pl.kolaboKSWZ.sportsscores

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

class MatchesListViewModel(val dataSource:DataSource) : ViewModel()
{
    val matchesiveData=dataSource.getMatchList()
    fun insertMatch(Team1Name: String?, Team1Photo: String?,Team1Score:String?,Team2Name:String?,Team2Photo:String?,Team2Score:String?)
    {
        if(Team1Name == null || Team1Photo==null || Team1Score==null || Team2Name==null || Team2Photo==null || Team2Score==null)
        {
            return
        }
        //val image=dataSource.getRandomTaskImageAsset(taskImage.toInt())
        val newMatch=Match(
            Random.nextInt(),
            Team1Name,
            Team1Photo.toInt(),
            Team1Score,
            Team2Name,
            Team2Photo.toInt(),
            Team2Score
        )
        dataSource.addMatch(newMatch)
    }
}

class TasksListViewModelFactory(private val context: Context) : ViewModelProvider.Factory
{
    override fun <T: ViewModel> create(modelClass:Class<T>): T{
        if(modelClass.isAssignableFrom(MatchesListViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return MatchesListViewModel(dataSource=DataSource.getDataSource(context.resources)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}