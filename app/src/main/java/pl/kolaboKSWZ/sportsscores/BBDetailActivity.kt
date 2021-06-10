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
        when(bundle!!.getInt("t1id")){
            1->binding.bbDetailTeam1.setImageResource(R.drawable.atl_log)
            2->binding.bbDetailTeam1.setImageResource(R.drawable.bos_log)
            3->binding.bbDetailTeam1.setImageResource(R.drawable.bkn_log)
            4->binding.bbDetailTeam1.setImageResource(R.drawable.cha_log)
            5->binding.bbDetailTeam1.setImageResource(R.drawable.chi_log)
            6->binding.bbDetailTeam1.setImageResource(R.drawable.cle_log)
            7->binding.bbDetailTeam1.setImageResource(R.drawable.dal_log)
            8->binding.bbDetailTeam1.setImageResource(R.drawable.den_log)
            9->binding.bbDetailTeam1.setImageResource(R.drawable.det_log)
            10->binding.bbDetailTeam1.setImageResource(R.drawable.gsw_log)
            11->binding.bbDetailTeam1.setImageResource(R.drawable.hou_log)
            12->binding.bbDetailTeam1.setImageResource(R.drawable.ind_log)
            13->binding.bbDetailTeam1.setImageResource(R.drawable.lac_log)
            14->binding.bbDetailTeam1.setImageResource(R.drawable.lal_log)
            15->binding.bbDetailTeam1.setImageResource(R.drawable.mem_log)
            16->binding.bbDetailTeam1.setImageResource(R.drawable.mia_log)
            17->binding.bbDetailTeam1.setImageResource(R.drawable.mil_log)
            18->binding.bbDetailTeam1.setImageResource(R.drawable.min_log)
            19->binding.bbDetailTeam1.setImageResource(R.drawable.nop_log)
            20->binding.bbDetailTeam1.setImageResource(R.drawable.nyk_log)
            21->binding.bbDetailTeam1.setImageResource(R.drawable.okc_log)
            22->binding.bbDetailTeam1.setImageResource(R.drawable.orl_log)
            23->binding.bbDetailTeam1.setImageResource(R.drawable.phi_log)
            24->binding.bbDetailTeam1.setImageResource(R.drawable.phx_log)
            25->binding.bbDetailTeam1.setImageResource(R.drawable.por_log)
            26->binding.bbDetailTeam1.setImageResource(R.drawable.sac_log)
            27->binding.bbDetailTeam1.setImageResource(R.drawable.sas_log)
            28->binding.bbDetailTeam1.setImageResource(R.drawable.tor_log)
            29->binding.bbDetailTeam1.setImageResource(R.drawable.uta_log)
            30->binding.bbDetailTeam1.setImageResource(R.drawable.was_log)
        }

        when(bundle.getInt("t2id")){
            1->binding.bbDetailTeam2.setImageResource(R.drawable.atl_log)
            2->binding.bbDetailTeam2.setImageResource(R.drawable.bos_log)
            3->binding.bbDetailTeam2.setImageResource(R.drawable.bkn_log)
            4->binding.bbDetailTeam2.setImageResource(R.drawable.cha_log)
            5->binding.bbDetailTeam2.setImageResource(R.drawable.chi_log)
            6->binding.bbDetailTeam2.setImageResource(R.drawable.cle_log)
            7->binding.bbDetailTeam2.setImageResource(R.drawable.dal_log)
            8->binding.bbDetailTeam2.setImageResource(R.drawable.den_log)
            9->binding.bbDetailTeam2.setImageResource(R.drawable.det_log)
            10->binding.bbDetailTeam2.setImageResource(R.drawable.gsw_log)
            11->binding.bbDetailTeam2.setImageResource(R.drawable.hou_log)
            12->binding.bbDetailTeam2.setImageResource(R.drawable.ind_log)
            13->binding.bbDetailTeam2.setImageResource(R.drawable.lac_log)
            14->binding.bbDetailTeam2.setImageResource(R.drawable.lal_log)
            15->binding.bbDetailTeam2.setImageResource(R.drawable.mem_log)
            16->binding.bbDetailTeam2.setImageResource(R.drawable.mia_log)
            17->binding.bbDetailTeam2.setImageResource(R.drawable.mil_log)
            18->binding.bbDetailTeam2.setImageResource(R.drawable.min_log)
            19->binding.bbDetailTeam2.setImageResource(R.drawable.nop_log)
            20->binding.bbDetailTeam2.setImageResource(R.drawable.nyk_log)
            21->binding.bbDetailTeam2.setImageResource(R.drawable.okc_log)
            22->binding.bbDetailTeam2.setImageResource(R.drawable.orl_log)
            23->binding.bbDetailTeam2.setImageResource(R.drawable.phi_log)
            24->binding.bbDetailTeam2.setImageResource(R.drawable.phx_log)
            25->binding.bbDetailTeam2.setImageResource(R.drawable.por_log)
            26->binding.bbDetailTeam2.setImageResource(R.drawable.sac_log)
            27->binding.bbDetailTeam2.setImageResource(R.drawable.sas_log)
            28->binding.bbDetailTeam2.setImageResource(R.drawable.tor_log)
            29->binding.bbDetailTeam2.setImageResource(R.drawable.uta_log)
            30->binding.bbDetailTeam2.setImageResource(R.drawable.was_log)
        }
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