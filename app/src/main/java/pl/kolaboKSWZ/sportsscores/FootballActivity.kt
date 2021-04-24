package pl.kolaboKSWZ.sportsscores

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import pl.kolaboKSWZ.sportsscores.databinding.ActivityFootballBinding


class FootballActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFootballBinding
    private var prevMenuItem : MenuItem? = null
    private var dateItem: MenuItem? = null

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
        if (item.itemId != R.id.date){
            prevMenuItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW)
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            supportActionBar?.title = item.title
            prevMenuItem = item

            when (item.itemId){
                R.id.eng-> {
                    binding.toolbar.setBackgroundColor(Color.parseColor("#A00000"))
                    binding.toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
                    dateItem?.setIcon(R.drawable.date_white)
                }
                R.id.sp-> {
                    binding.toolbar.setBackgroundColor(Color.parseColor("#800000"))
                    binding.toolbar.setTitleTextColor(Color.parseColor("#FFD300"))
                    dateItem?.setIcon(R.drawable.date_yellow)
                }
                R.id.ita-> {
                    binding.toolbar.setBackgroundColor(Color.parseColor("#1261A0"))
                    binding.toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
                    dateItem?.setIcon(R.drawable.date_white)
                }
                R.id.ger-> {
                    binding.toolbar.setBackgroundColor(Color.parseColor("#343434"))
                    binding.toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
                    dateItem?.setIcon(R.drawable.date_white)
                }
                R.id.world-> {
                    binding.toolbar.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.toolbar.setTitleTextColor(Color.parseColor("#000000"))
                    dateItem?.setIcon(R.drawable.date)
                }
            }
        }
        else{

        }
        return true
    }
}