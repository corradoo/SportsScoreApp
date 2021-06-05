package pl.kolaboKSWZ.sportsscores

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.net.URL
import kotlin.random.Random


class DataSource(resources: Resources,context: Context) {

    private lateinit var database : AppDatabase
    private var myContext = context.applicationContext
    private var initialMatchList = initMatchesList()
    private var matchLiveData = MutableLiveData(initialMatchList)

    init {
        //setMatchesData("all")
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

    fun setMatchesData(type : String, date : String){
        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches4.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }
            var result : List<Match>? = null
            when (type){
                "all" -> result = database.matchDAO().getAll( date + "%")
                "ger" -> result = database.matchDAO().getGer(date + "%")
                "eng" -> result = database.matchDAO().getEng(date + "%")
                "ita" -> result = database.matchDAO().getIta(date + "%")
                "spa" -> result = database.matchDAO().getSpa(date + "%")
            }
            matchLiveData.postValue(result)
        }
    }

    fun insertMatches(list : List<Match>, league : Int, date: String) {

        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches4.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }

            for (i in list) {
                System.out.println(i.matchID)
                database.matchDAO().insertAll(i)
            }
            //allMatches()
            when(league) {
                3260 -> setMatchesData("eng", date)
                3229 -> setMatchesData("spa", date)
                3218 -> setMatchesData("ger", date)
                3241 -> setMatchesData("ita", date)
                -1 -> setMatchesData("all", date)
            }



        }
    }

    //Premier League 3260     LaLiga 3229    	Bundesliga 3218      	Serie A 3241
    fun api(date : String, league : Int) {
        val thread = Thread {
            try {
                val token = getToken()
                val client = OkHttpClient()
                val url = URL("https://football.elenasport.io/v2/seasons/$league/fixtures?expand=away_team,home_team&from=$date")

                val request = Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer $token")
                    .get()
                    .build()

                val response = client.newCall(request).execute()

                val responseBody = response.body()!!.string()
                //Response
                println("Response Body: $responseBody")

                //we could use jackson if we got a JSON
                val mapperAll = ObjectMapper()
                val objData = mapperAll.readTree(responseBody)

                val apiMatches = arrayListOf<Match>()

                objData.get("data").forEachIndexed { index, jsonNode ->
                    println("$index: ${jsonNode.get("homeName")} vs ${jsonNode.get("awayName")}")
                    apiMatches.add(Match(
                        matchID = jsonNode.get("id").asInt(),
                        seasonID = jsonNode.get("idSeason").asInt(),
                        venue = jsonNode.get("venueName").toString().replace("\"", ""),
                        date = jsonNode.get("date").toString().replace("\"", ""),

                        Team1Name = jsonNode.get("homeName").toString().replace("\"", ""),
                        Team1Photo = jsonNode.get("expand").get("home_team")[0].get("badgeURL").toString().replace("\"", ""),
                        Team1Score = jsonNode.get("team_home_90min_goals").toString(),
                        team_home_1stHalf_goals = jsonNode.get("team_home_1stHalf_goals").asInt(),
                        team_home_2ndHalf_goals = jsonNode.get("team_home_2ndHalf_goals").asInt(),

                        Team2Name = jsonNode.get("awayName").toString().replace("\"", ""),
                        Team2Photo = jsonNode.get("expand").get("away_team")[0].get("badgeURL").toString().replace("\"", ""),
                        Team2Score = jsonNode.get("team_away_90min_goals").toString(),
                        team_away_1stHalf_goals = jsonNode.get("team_away_1stHalf_goals").asInt(),
                        team_away_2ndHalf_goals = jsonNode.get("team_away_2ndHalf_goals").asInt(),
                    ))
                }
                insertMatches(apiMatches, league, date)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()

    }

    fun getToken() : String {
        val client = OkHttpClient()
        val url = URL("https://oauth2.elenasport.io/oauth2/token")

        val formBody: RequestBody = FormBody.Builder()
            .add("grant_type", "client_credentials")
            .build()

        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Basic cWRtMTFoMTBycTRwMHBrMnM4dmJlNGtzYzoxNjY1cmwxcmRidm9qNXNpZGNlMWNqZTRxbGtsb25ldnNsYmkycmVicHNkY25idnJrdDJq")
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .post(formBody)
            .build()

        val response = client.newCall(request).execute()

        val responseBody = response.body()!!.string()
        //Response
        println("Response Body: $responseBody")

        val mapperAll = ObjectMapper()
        val objData = mapperAll.readTree(responseBody)
        val token = objData.get("access_token").toString().replace("\"", "")
        Log.d("token", token)
        return token
    }

    fun initMatchesList() : List<Match>
    {
        return listOf(
            Match(
                matchID= Random.nextInt(),
                seasonID=1,
                venue = "Bernabeu",
                date = "2021-05-01 16:00:00",

                Team1Name="Hiszpania",
                Team1Photo="https://lh3.googleusercontent.com/proxy/FFqm26F5Ov3OBbgpBLy3NZLtnuRQCJGl5PfXxh1G-MDh5vXWtJ8KNfr5T8iDOSSBef2HRGJ0UFfbxZ5LrAvwC5ks4PgOM7GknmJOevgjqYiCmkIz",
                Team1Score="3",
                Team2Name="Anglia",
                Team2Photo="https://lh3.googleusercontent.com/proxy/FFqm26F5Ov3OBbgpBLy3NZLtnuRQCJGl5PfXxh1G-MDh5vXWtJ8KNfr5T8iDOSSBef2HRGJ0UFfbxZ5LrAvwC5ks4PgOM7GknmJOevgjqYiCmkIz",
                Team2Score="2",


                team_home_1stHalf_goals = 3,
                team_home_2ndHalf_goals = 0,

                team_away_1stHalf_goals = 1,
                team_away_2ndHalf_goals = 1,
            )

        )
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
}

//TO-DO: API connection
//eyJraWQiOiJXVjdcL3pXR0FydzdsR1wvUU9tT1wvY1JPYnZoZ2ZSa1JtVnlLVk80RXBkR2lrPSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiJxZG0xMWgxMHJxNHAwcGsyczh2YmU0a3NjIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwic2NvcGUiOiJmb290YmFsbC5lbGVuYXNwb3J0LmlvXC9HRVQ6KiIsImF1dGhfdGltZSI6MTYyMTI3OTM0MywiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmV1LXdlc3QtMS5hbWF6b25hd3MuY29tXC9ldS13ZXN0LTFfd080Q0J3N0x3IiwiZXhwIjoxNjIxMjgyOTQzLCJpYXQiOjE2MjEyNzkzNDMsInZlcnNpb24iOjIsImp0aSI6IjU0NTdjOGI1LTY0OTQtNDkyNS04ZGMyLWEzNzllYzRjMGExYyIsImNsaWVudF9pZCI6InFkbTExaDEwcnE0cDBwazJzOHZiZTRrc2MifQ.XOhSr9EOxAu2x_WofhS0Spoz8rlRucjT1iWHUc3J1vHPzMIjSejk7d9CDNvVCyt2P7eCDu3MAnVMnjxblCulDjkyEAyuk0hRWTF9_MpT93iSYWloJ7tNuAPutt-AiFt-RNeKV-l5lRt961yBCWgGdqzd1KrdjW0f-RzEVzEDbRAFoVbXL6V5xZkAIWU_f52rTJzRac_QxpUHCluhvguAMnW1zT9lKV8fwf1OfPooKioI4JaxFFzCVyWuK9S8gzSoCGLPMZe-5JwRR8xrdAjbj3l5P1rLjWXLYWYhfnJu0HKsJX_2SXayZoLNBXX5CXuFOzQUeTAm0FImAOg344bmhQ


// 16 {"id":3795,"idLeague":514,"leagueName":"Primera División - 2021","start":2021,"end":2021} -- venezuela
//https://football.elenasport.io/v2/leagues/514/seasons
//234	Premier League 3260
//466   LaLiga 3229
//272	Bundesliga 3218 - obecny sezon, 2025 stage
//318	Serie A 3241
//https://football.elenasport.io/v2/stages/:id - Useless
//https://football.elenasport.io/v2/teams/:id - Info o drużynie
// https://football.elenasport.io/v2/fixtures
// The Fixture endpoints return information about football games.
// In these endpoints, there are many parameters available to allow you to filter by team, date, round and head2head.
//https://football.elenasport.io/v2/upcoming
//https://football.elenasport.io/v2/stages/2025/standing - tabela bundesligi
//https://football.elenasport.io/v2/seasons/3218/upcoming - nadchodzące mecze bundesligi
//https://football.elenasport.io/v2/seasons/3218/inplay - trwajace w bundeslidze
//https://football.elenasport.io/v2/seasons/3218/fixtures - wszystkie mecze w danym sezonie
//https://football.elenasport.io/v2/teams/156 - Bayern info o drużynie plus ikonka w linku


/*fun allMatches()
    {

        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches3.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }
            matchLiveData.postValue(database.matchDAO().getAll())
        }
    }

    fun ger() {

        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches3.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }
            matchLiveData.postValue(database.matchDAO().getGer())
        }
    }

    fun eng() {
        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches3.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }
            matchLiveData.postValue(database.matchDAO().getEng())
        }
    }

    fun ita()
    {
        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches3.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }
            matchLiveData.postValue(database.matchDAO().getIta())
        }
    }

    fun spa()
    {
        GlobalScope.launch {
            try {
                database = Room.databaseBuilder(
                    myContext,
                    AppDatabase::class.java,
                    "Matches3.db"
                ).build()
            } catch (e: Exception) {
                Log.d("Ravab", e.message.toString())
            }
            matchLiveData.postValue(database.matchDAO().getSpa())
        }
    }*/