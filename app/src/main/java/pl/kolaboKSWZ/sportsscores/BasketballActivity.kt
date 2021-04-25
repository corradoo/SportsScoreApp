package pl.kolaboKSWZ. sportsscores

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import pl.kolaboKSWZ.sportsscores.databinding.ActivityBasketballBinding

class BasketballActivity :AppCompatActivity(){

    private lateinit var binding: ActivityBasketballBinding
    private var prevItem: MenuItem? =null
    private var dateItem: MenuItem? =null
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBasketballBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbarBB)
        recyclerView=findViewById(R.id.recycler_bb)


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
                    dateItem?.isVisible = false
                }
                R.id.stand_east->{
                    binding.toolbarBB.setBackgroundColor(resources.getColor(R.color.blue))
                    binding.toolbarBB.setTitleTextColor(resources.getColor(R.color.white))
                    dateItem?.isVisible = false
                }
                R.id.logo_bb->{
                    binding.toolbarBB.setBackgroundColor(resources.getColor(R.color.white))
                    binding.toolbarBB.setTitleTextColor(resources.getColor(R.color.black))
                    dateItem?.isVisible = true
                    dateItem?.setIcon(R.drawable.date)
                }

            }

        }
        else{

        }
        return true
    }




}