package pl.kolaboKSWZ.sportsscores

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kolaboKSWZ.sportsscores.BBMatch
import pl.kolaboKSWZ.sportsscores.BBMatchesAdapter
import pl.kolaboKSWZ.sportsscores.R
import pl.kolaboKSWZ.sportsscores.databinding.ActivityBasketballBinding

class BasketballActivity :AppCompatActivity(), BBMatchesAdapter.OnItemClickListener{

    private lateinit var binding: ActivityBasketballBinding
    private var prevItem: MenuItem? =null
    private var dateItem: MenuItem? =null
    private lateinit var recyclerView: RecyclerView
    private val matchesList=ArrayList<BBMatch>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBasketballBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbarBB)
        recyclerView=findViewById(R.id.recycler_bb)
        binding.recyclerBb.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerBb.adapter=BBMatchesAdapter(matchesList,this)
//        val match: BBMatch=(1,"le","10","ww","20","WW")
//        matchesList.add(pl.kolaboKSWZ.sportsscores.BBMatch(1,"le","10","ww","20","WW"))

//        Toast.makeText(this, "ee", Toast.LENGTH_SHORT).show()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.basketball_menu,menu)
        prevItem=menu?.findItem(R.id.logo_bb)
        dateItem=menu?.findItem(R.id.date_bb)
        dateItem?.setIcon(R.drawable.date)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId!= R.id.date_bb){
            prevItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW)
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            supportActionBar?.title=item.title
            prevItem=item

            when (item.itemId){
                R.id.stand_west->{
                    binding.toolbarBB.setBackgroundColor(resources.getColor(R.color.red))
                    binding.toolbarBB.setTitleTextColor(resources.getColor(R.color.white))
                    recyclerView.setBackgroundResource(R.color.west_bg)
                    dateItem?.isVisible = false
                }
                R.id.stand_east->{
                    binding.toolbarBB.setBackgroundColor(resources.getColor(R.color.blue))
                    binding.toolbarBB.setTitleTextColor(resources.getColor(R.color.white))
                    recyclerView.setBackgroundResource(R.color.east_bg)
                    dateItem?.isVisible = false
                }
                R.id.logo_bb->{
                    binding.toolbarBB.setBackgroundColor(resources.getColor(R.color.toolbar_bb))
                    binding.toolbarBB.setTitleTextColor(resources.getColor(R.color.black))
                    recyclerView.setBackgroundResource(R.color.grey)
                    dateItem?.isVisible = true
                    dateItem?.setIcon(R.drawable.date)
                }

            }

        }
        else{

        }
        return true
    }

    override fun onItemClick(position: Int) {
//        val intent= Intent(this,BBDetailActivity::class.java)
//        intent.putExtra("test",matchesList[position].matchID)
//        startActivity(intent)
        println(position)
        println(matchesList[position].date+" pos: "+position)
        val intent= Intent(this,BBDetailActivity::class.java)
        startActivity(intent)
//        Toast.makeText(this, "position", Toast.LENGTH_SHORT).show()
    }

    fun fabClick(view: View){
        matchesList.add(BBMatch(1,"LAL","110","GSW","98","LAL","2020-05-15"))
        binding.recyclerBb.adapter?.notifyDataSetChanged()

    }


}