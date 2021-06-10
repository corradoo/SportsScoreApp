package pl.kolaboKSWZ.sportsscores

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import pl.kolaboKSWZ.sportsscores.BBMatch
import pl.kolaboKSWZ.sportsscores.BBMatchesAdapter
import pl.kolaboKSWZ.sportsscores.R
import pl.kolaboKSWZ.sportsscores.databinding.ActivityBasketballBinding
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class BasketballActivity :AppCompatActivity(), BBMatchesAdapter.OnItemClickListener, DatePickerDialog.OnDateSetListener{

    private lateinit var binding: ActivityBasketballBinding
    private var prevItem: MenuItem? =null
    private var dateItem: MenuItem? =null
    private lateinit var recyclerView: RecyclerView
    private val matchesList=ArrayList<BBMatch>()

    var day = 0; var month = 0; var year = 0; var pickedDay = -1; var pickedMonth = -1
    var pickedYear = -1; var formattedDay = ""; var formattedMonth = ""
    lateinit var date : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBasketballBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbarBB)
        recyclerView=findViewById(R.id.recycler_bb)
        binding.recyclerBb.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerBb.adapter=BBMatchesAdapter(matchesList,this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.basketball_menu,menu)
        prevItem=menu?.findItem(R.id.logo_bb)

        dateItem?.setIcon(R.drawable.date)
        return true
    }


    override fun onItemClick(position: Int) {

        val intent= Intent(this,BBDetailActivity::class.java)
        intent.putExtra("gameId",matchesList[position].matchID)
        intent.putExtra("pts","${matchesList[position].team1Score} - ${matchesList[position].team2Score}")
        intent.putExtra("t1Name",matchesList[position].team1Name)
        intent.putExtra("t2Name",matchesList[position].team2Name)
        intent.putExtra("t1id",matchesList[position].team1id)
        intent.putExtra("t2id",matchesList[position].team2id)
        startActivity(intent)
    }

    fun api() {
        val thread = Thread {
            try {


                updateDateTime()
                if(pickedDay==-1 || pickedMonth==-1 || pickedYear==-1){
                    pickedDay=day
                    pickedMonth=month
                    pickedYear=year
                }
                matchesList.clear()
                setDateValues()

                val client = OkHttpClient()
                val url = URL("https://www.balldontlie.io/api/v1/games?seasons[]=2020&start_date=$date")

                val request = Request.Builder()
                    .url(url)
                    .get()
                    .build()

                val response = client.newCall(request).execute()

                val responseBody = response.body()!!.string()

                val mapperAll = ObjectMapper()
                val objData = mapperAll.readTree(responseBody)

                val apiMatches = arrayListOf<BBMatch>()


                objData.get("data").forEachIndexed { _, jsonNode ->

                    val fullDate = jsonNode.get("date").toString().replace("\"", "").split("T")
                    apiMatches.add(BBMatch(
                        matchID = jsonNode.get("id").asInt(),
                        matchLocation = jsonNode.get("home_team").get("abbreviation").toString().replace("\"", ""),
                        date = fullDate[0],

                        team1Name = jsonNode.get("home_team").get("full_name").toString().replace("\"", ""),
                        team1Score = jsonNode.get("home_team_score").toString(),
                        team1id = jsonNode.get("home_team").get("id").asInt(),

                        team2Name = jsonNode.get("visitor_team").get("full_name").toString().replace("\"", ""),
                        team2Score = jsonNode.get("visitor_team_score").toString(),
                        team2id = jsonNode.get("visitor_team").get("id").asInt(),
                    ))
                }
                matchesList.addAll(apiMatches)
                binding.recyclerBb.adapter?.notifyDataSetChanged()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()
    }

    fun BBDateClick(view: View){
        updateDateTime()
        DatePickerDialog(this,this,year,month,day).show()
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
        binding.BBDateButton.text = "$formattedDay.$formattedMonth.$pickedYear"
        date = "$pickedYear-$formattedMonth-$formattedDay"
    }



    fun getApi(item: MenuItem) {
        api()
    }

}