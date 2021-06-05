package pl.kolaboKSWZ.sportsscores

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kolaboKSWZ.sportsscores.databinding.ActivityFootballBinding
import java.security.AccessController.getContext
import java.util.*


class FootballActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityFootballBinding
    private var prevMenuItem: MenuItem? = null
    private var dateItem: MenuItem? = null
    private val matchesListViewModel by viewModels<MatchesListViewModel> {
        MatchesListViewModelFactory(this)
    }
    var day = 0; var month = 0; var year = 0; var pickedDay = -1; var pickedMonth = -1
    var pickedYear = -1; var formattedDay = ""; var formattedMonth = ""
    var clicked = false
    var mainColor = Color.parseColor("#FFFFFF")
    var secondColor = Color.parseColor("#000000")

    lateinit var date : String
    var idLeague : Int = -1
    var leagueName = "all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFootballBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        val fg = EmptyFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.myFragment, fg)
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
        updateDateTime()
        pickedDay = day
        pickedMonth = month
        pickedYear = year
        setDateValues()
        setLeague(Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), R.drawable.fifa, "all",-1)
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
            R.id.eng -> setLeague(Color.parseColor("#A00000"), Color.parseColor("#FFFFFF"), R.drawable.premier, "eng",3260)
            R.id.sp -> setLeague(Color.parseColor("#800000"), Color.parseColor("#FFD300"), R.drawable.la_liga, "spa",3229)
            R.id.ita -> setLeague(Color.parseColor("#1261A0"), Color.parseColor("#FFFFFF"), R.drawable.serie, "ita",3241)
            R.id.ger -> setLeague(Color.parseColor("#343434"), Color.parseColor("#FFFFFF"), R.drawable.bundesliga, "ger",3218)
            R.id.world -> setLeague(Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), R.drawable.fifa, "all", -1)
        }
        return true
    }

    fun setLeague(firstColor: Int, secondColor: Int, image: Int, league: String, id : Int){
        mainColor = firstColor
        this.secondColor = secondColor
        leagueName = league
        binding.toolbar.setBackgroundColor(firstColor)
        binding.toolbar.setTitleTextColor(secondColor)
        binding.dateButton.setBackgroundColor(firstColor)
        binding.dateButton.setTextColor(secondColor)
        binding.bgImage.setImageResource(image)
        matchesListViewModel.dataSource.setMatchesData(league, date)
        System.out.println(date)

        idLeague = id
    }

    fun getMatches(item: MenuItem) {
        matchesListViewModel.dataSource.api(date,idLeague)
    }

    private fun adapterOnClick(match: Match) {
        if (clicked){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.myFragment, EmptyFragment())
                commit()
            }
            clicked = false
        }
    }

    private fun adapterLongClick(match: Match){
        if (!clicked) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.myFragment, TestFragment(match.Team1Name, match.Team1Score, match.Team1Photo, match.Team2Name,
                    match.Team2Score, match.Team2Photo, mainColor,secondColor,match.date))
                commit()
            }
            clicked = true
        }
    }

    fun dateClick(view: View) {
       // updateDateTime()
        //DatePickerDialog(this, this, year, month, day).show()
        DatePickerDialog(this, this, pickedYear, pickedMonth, pickedDay).show()
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
        date = "$pickedYear-$formattedMonth-$formattedDay"

        matchesListViewModel.dataSource.setMatchesData(leagueName, date)
    }
}
