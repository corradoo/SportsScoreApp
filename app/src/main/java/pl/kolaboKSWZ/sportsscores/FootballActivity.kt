package pl.kolaboKSWZ.sportsscores

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pl.kolaboKSWZ.sportsscores.databinding.ActivityFootballBinding
import java.util.*

const val MATCH_ID="match_id"
class FootballActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityFootballBinding
    private val matchesListViewModel by viewModels<MatchesListViewModel> {
        MatchesListViewModelFactory(this)
    }
    private var prevMenuItem: MenuItem? = null
    private var currentMenuItemId = R.id.world
    lateinit var date : String
    private var clicked = false
    
    var pickedDay = -1; var pickedMonth = -1; var pickedYear = -1;
    var mainColor = Color.parseColor("#FFFFFF")
    var otherColor = Color.parseColor("#000000")
    var leagueName = "all"

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("currentMenuItemId", currentMenuItemId)
        outState.putString("date", date)
        outState.putInt("day", pickedDay)
        outState.putInt("month", pickedMonth)
        outState.putInt("year", pickedYear)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run {
            currentMenuItemId = getInt("currentMenuItemId")
            date = getString("date").toString()
            pickedDay = getInt("day")
            pickedMonth = getInt("month")
            pickedYear = getInt("year")
            setDateValues(false)
            prepareSetLeague(currentMenuItemId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFootballBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.myFragment, EmptyFragment())
            commit()
        }

        val matchesAdapter = MatchesAdapter(
            onClick = {match -> adapterOnClick(match) },
            longClick = {match -> adapterLongClick(match) }
        )
        binding.recyclerView.adapter = matchesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        matchesListViewModel.matchesLiveData.observe(this, {
            it?.let {
                matchesAdapter.submitList(it as MutableList<Match>)
            }
        })

        if (savedInstanceState == null){
            val cal: Calendar = Calendar.getInstance()
            pickedDay = cal.get(Calendar.DAY_OF_MONTH)
            pickedMonth = cal.get(Calendar.MONTH)
            pickedYear = cal.get(Calendar.YEAR)
            setDateValues(false)
            prepareSetLeague(currentMenuItemId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.football_menu, menu)
        menu?.findItem(currentMenuItemId)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        prevMenuItem = menu?.findItem(currentMenuItemId)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        prevMenuItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        prevMenuItem = item
        prepareSetLeague(item.itemId)
        return true
    }

    fun prepareSetLeague(id : Int){
        val tb = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        supportActionBar?.title = tb.menu.findItem(id).title
        currentMenuItemId = id
        when (id) {
            R.id.eng -> setLeague(Color.parseColor("#A00000"), Color.parseColor("#FFFFFF"), R.drawable.premier, "eng")
            R.id.sp -> setLeague(Color.parseColor("#800000"), Color.parseColor("#FFD300"), R.drawable.la_liga, "spa")
            R.id.ita -> setLeague(Color.parseColor("#1261A0"), Color.parseColor("#FFFFFF"), R.drawable.serie, "ita")
            R.id.ger -> setLeague(Color.parseColor("#343434"), Color.parseColor("#FFFFFF"), R.drawable.bundesliga, "ger")
            R.id.world -> setLeague(Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), R.drawable.fifa, "all")
        }
    }

    fun setLeague(firstColor: Int, secondColor: Int, image: Int, league: String){
        mainColor = firstColor
        this.otherColor = secondColor
        leagueName = league
        binding.toolbar.setBackgroundColor(firstColor)
        binding.toolbar.setTitleTextColor(secondColor)
        binding.dateButton.setBackgroundColor(firstColor)
        binding.dateButton.setTextColor(secondColor)
        binding.bgImage.setImageResource(image)
        matchesListViewModel.dataSource.setMatchesData(league, date)
    }

    fun getMatches(item: MenuItem) {
        when (leagueName){
            "all" -> matchesListViewModel.dataSource.api(date, -1)
            "eng" -> matchesListViewModel.dataSource.api(date, 3260)
            "spa" -> matchesListViewModel.dataSource.api(date, 3229)
            "ita" -> matchesListViewModel.dataSource.api(date, 3241)
            "ger" -> matchesListViewModel.dataSource.api(date, 3218)
        }
    }

    private fun adapterOnClick(match: Match) {
        if (clicked){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.myFragment, EmptyFragment())
                commit()
            }
            clicked = false
        }
        else {
            val intent = Intent(this, MatchDetailActivity()::class.java)
            intent.putExtra(MATCH_ID, match.matchID)
            intent.putExtra("mainColor", mainColor)
            intent.putExtra("secondColor", otherColor)
            startActivity(intent)
        }
    }

    private fun adapterLongClick(match: Match){
        if (!clicked) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.myFragment, MatchFragment(match.Team1Name, match.Team1Score, match.Team1Photo, match.Team2Name,
                    match.Team2Score, match.Team2Photo, mainColor,otherColor,match.date))
                commit()
            }
            clicked = true
        }
    }

    fun dateClick(view: View) {
        DatePickerDialog(this, this, pickedYear, pickedMonth, pickedDay).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        pickedDay = dayOfMonth
        pickedMonth = month
        pickedYear = year
        setDateValues(true)
    }

    fun setDateValues(cond: Boolean) {
        var formattedDay = ""
        var formattedMonth = ""
        if (pickedDay < 10) {
            formattedDay += "0"
        }
        if (pickedMonth < 10) {
            formattedMonth += "0"
        }
        formattedDay += pickedDay
        formattedMonth += (pickedMonth + 1)
        binding.dateButton.text = "$formattedDay.$formattedMonth.$pickedYear"
        date = "$pickedYear-$formattedMonth-$formattedDay"

        if (cond)
            matchesListViewModel.dataSource.setMatchesData(leagueName, date)
    }
}
