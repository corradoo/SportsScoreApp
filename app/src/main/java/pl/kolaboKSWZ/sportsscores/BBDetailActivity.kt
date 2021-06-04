package pl.kolaboKSWZ.sportsscores

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.w3c.dom.Text
import pl.kolaboKSWZ.sportsscores.databinding.BasketballMatchDetailBinding
import java.net.URL
import kotlin.properties.Delegates

class BBDetailActivity: AppCompatActivity() /**,BBDetailAdapter.OnItemClickListener**/ {
    private lateinit var binding: BasketballMatchDetailBinding
    private lateinit var recyclerView: RecyclerView
    private val playersList= ArrayList<BBPlayer>()
    private var gameID by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= BasketballMatchDetailBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        recyclerView=findViewById(R.id.bb_match_detail_recycler)
        binding.bbMatchDetailRecycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.bbMatchDetailRecycler.adapter=BBDetailAdapter(playersList)
        val bundle: Bundle?= intent.extras
        binding.bbMatchScore.text = bundle?.getString("pts")
        binding.detailTeam1Name.text = bundle?.getString("t1Name")
        binding.detailTeam2Name.text = bundle?.getString("t2Name")
        gameID = bundle!!.getInt("gameId")
        api()
    }

    fun api() {
        val thread = Thread {
            try {

                val client = OkHttpClient()
                val url = URL("https://www.balldontlie.io/api/v1/stats?game_ids[]=$gameID")

                val request = Request.Builder()
                    .url(url)
                    .get()
                    .build()

                val response = client.newCall(request).execute()

                val responseBody = response.body()!!.string()
                //Response
                println("Response Body: $responseBody")

                //we could use jackson if we got a JSON
                val mapperAll = ObjectMapper()
                val objData = mapperAll.readTree(responseBody)

                val apiMatches = arrayListOf<BBPlayer>()

                objData.get("data").forEachIndexed { index, jsonNode ->
                    apiMatches.add(BBPlayer(
                        playerID = jsonNode.get("id").asInt(),
                        playerFirstName = jsonNode.get("player").get("first_name").toString().replace("\"", ""),
                        playerLastName = jsonNode.get("player").get("last_name").toString().replace("\"", ""),

                        playerPosition = jsonNode.get("player").get("position").toString().replace("\"", ""),
                        playerTeamID = jsonNode.get("player").get("team_id").asInt(),
                        points = jsonNode.get("pts").asInt(),
                        rbs = jsonNode.get("reb").asInt(),
                        steals = jsonNode.get("stl").asInt(),
                        assists = jsonNode.get("ast").asInt(),
                        minutes = jsonNode.get("min").toString().replace("\"", ""),


                    ))
                }
                playersList.addAll(apiMatches)
                binding.bbMatchDetailRecycler.adapter?.notifyDataSetChanged()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()
    }
}