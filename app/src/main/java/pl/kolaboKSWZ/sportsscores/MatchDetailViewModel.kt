
package pl.kolaboKSWZ.sportsscores


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MatchDetailViewModel(private val datasource:DataSource) : ViewModel() {
    fun getMatchForId(id:Int) : Match?
    {
        return datasource.getMatchForId(id)
    }
}

class MatchDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory
{
    override fun <T: ViewModel> create(modelClass:Class<T>) : T{
        if (modelClass.isAssignableFrom(MatchDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MatchDetailViewModel(
                datasource=DataSource.getDataSource(context.resources,context)
            ) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}