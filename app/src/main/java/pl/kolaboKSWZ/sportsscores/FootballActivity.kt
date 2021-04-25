package pl.kolaboKSWZ.sportsscores

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import pl.kolaboKSWZ.sportsscores.databinding.ActivityFootballBinding
import java.util.*


class FootballActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: ActivityFootballBinding
    private var prevMenuItem: MenuItem? = null
    private var dateItem: MenuItem? = null

    var day = 0; var month = 0; var year = 0;
    var pickedDay = -1; var pickedMonth = -1; var pickedYear = -1;
    var formattedDay = ""; var formattedMonth = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFootballBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.football_menu, menu)
        prevMenuItem = menu?.findItem(R.id.world)
        dateItem = menu?.findItem(R.id.date)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        prevMenuItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        supportActionBar?.title = item.title
        prevMenuItem = item

        when (item.itemId) {
            R.id.eng -> {
                binding.toolbar.setBackgroundColor(Color.parseColor("#A00000"))
                binding.toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
                binding.dateButton.setBackgroundColor(Color.parseColor("#A00000"))
                binding.dateButton.setTextColor(Color.parseColor("#FFFFFF"))
                binding.bgImage.setImageResource(R.drawable.premier)
            }
            R.id.sp -> {
                binding.toolbar.setBackgroundColor(Color.parseColor("#800000"))
                binding.toolbar.setTitleTextColor(Color.parseColor("#FFD300"))
                binding.dateButton.setBackgroundColor(Color.parseColor("#800000"))
                binding.dateButton.setTextColor(Color.parseColor("#FFD300"))
                binding.bgImage.setImageResource(R.drawable.la_liga)
            }
            R.id.ita -> {
                binding.toolbar.setBackgroundColor(Color.parseColor("#1261A0"))
                binding.toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
                binding.dateButton.setBackgroundColor(Color.parseColor("#1261A0"))
                binding.dateButton.setTextColor(Color.parseColor("#FFFFFF"))
                binding.bgImage.setImageResource(R.drawable.serie)
            }
            R.id.ger -> {
                binding.toolbar.setBackgroundColor(Color.parseColor("#343434"))
                binding.toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
                binding.dateButton.setBackgroundColor(Color.parseColor("#343434"))
                binding.dateButton.setTextColor(Color.parseColor("#FFFFFF"))
                binding.bgImage.setImageResource(R.drawable.bundesliga)
            }
            R.id.world -> {
                binding.toolbar.setBackgroundColor(Color.parseColor("#FFFFFF"))
                binding.toolbar.setTitleTextColor(Color.parseColor("#000000"))
                binding.dateButton.setBackgroundColor(Color.parseColor("#FFFFFF"))
                binding.dateButton.setTextColor(Color.parseColor("#000000"))
                binding.bgImage.setImageResource(R.drawable.fifa)
            }
        }
        return true
    }

    fun dateClick(view: View) {
        updateDateTime()
        DatePickerDialog(this, this, year, month, day).show()
    }

    private fun updateDateTime(){
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        pickedDay = dayOfMonth
        pickedMonth = month
        pickedYear = year
        setDateValues()
    }

    fun setDateValues() {
        formattedDay = ""
        formattedMonth = ""
        if (pickedDay < 10) {
            formattedDay += "0"
        }
        if (pickedMonth < 10) {
            formattedMonth += "0"
        }
        formattedDay += pickedDay
        formattedMonth += (pickedMonth + 1)
        binding.dateButton.text = "$formattedDay.$formattedMonth.$pickedYear"
    }
}
