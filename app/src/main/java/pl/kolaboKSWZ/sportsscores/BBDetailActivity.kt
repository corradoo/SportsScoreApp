package pl.kolaboKSWZ.sportsscores

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import pl.kolaboKSWZ.sportsscores.databinding.BasketballMatchDetailBinding

class BBDetailActivity: AppCompatActivity() /**,BBDetailAdapter.OnItemClickListener**/ {
    private lateinit var binding: BasketballMatchDetailBinding
    private lateinit var recyclerView: RecyclerView
    private val playersList= ArrayList<BBPlayer>()
//    private lateinit var test: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= BasketballMatchDetailBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        recyclerView=findViewById(R.id.bb_match_detail_recycler)
        binding.bbMatchDetailRecycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.bbMatchDetailRecycler.adapter=BBDetailAdapter(playersList)
//        test= view.findViewById(R.id.bb_match_date)
        val bundle: Bundle?= intent.extras


        addPlayers()
    }

//    override fun onItemClick(position: Int) {
//        TODO("Not yet implemented")
//    }

    fun addPlayers(){
        playersList.add(BBPlayer(1,"Steph","Curry","PG",10))
        playersList.add(BBPlayer(1,"Steph","Curry","PG",10))
        playersList.add(BBPlayer(1,"Steph","Curry","PG",10))
        binding.bbMatchDetailRecycler.adapter?.notifyDataSetChanged()
    }
}