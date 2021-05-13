package pl.kolaboKSWZ.sportsscores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun football(view: View) {
        val myIntent = Intent(this, FootballActivity::class.java)
        startActivity(myIntent)
    }

    fun basketball(view: View) {
        val myIntent = Intent(this, BasketballActivity::class.java)
        startActivity(myIntent)
    }

}