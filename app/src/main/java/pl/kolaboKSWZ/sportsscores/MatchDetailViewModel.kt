
package pl.kolaboKSWZ.sportsscores


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskDetailViewModel(private val datasource:DataSource) : ViewModel() {
    fun getMatchForId(id:Int) : Match?
    {
        return datasource.getMatchForId(id)
    }
}

class TaskDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory
{
    override fun <T: ViewModel> create(modelClass:Class<T>) : T{
        if (modelClass.isAssignableFrom(TaskDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskDetailViewModel(
                datasource=DataSource.getDataSource(context.resources,context)
            ) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}