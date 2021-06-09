package pl.kolaboKSWZ.sportsscores

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.TextView
import androidx.activity.viewModels

class MatchDetailActivity() : AppCompatActivity() {

    private val matchDetailViewModel by viewModels<MatchDetailViewModel>
    {
        MatchDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        var currentMatchId: Int? = null
        var mainColor = 0
        var secondColor = 0
        val venue: TextView=findViewById(R.id.venueDetail)
        val firstHalfTeam1: TextView=findViewById(R.id.firstHalfTeam1)
        val firstHalfTeam2: TextView=findViewById(R.id.firstHalfTeam2)
        val secondHalfTeam1: TextView=findViewById(R.id.secondHalfTeam1)
        val secondHalfTeam2: TextView=findViewById(R.id.secondHalfTeam2)
        val bundle: Bundle?=intent.extras

        if(bundle!=null)
        {
            currentMatchId=bundle.getInt(MATCH_ID)
            mainColor = bundle.getInt("mainColor")
            secondColor = bundle.getInt("secondColor")
        }

        currentMatchId?.let{
            val currentMatch=matchDetailViewModel.getMatchForId(it)
            var temp = mainColor
            if (temp == Color.WHITE)
                temp = Color.BLACK
            System.out.println(currentMatch?.matchID)
            venue.text=currentMatch?.venue
            venue.setTextColor(temp)
            firstHalfTeam1.text=currentMatch?.team_home_1stHalf_goals.toString()
            firstHalfTeam1.setTextColor(temp)
            firstHalfTeam2.text=currentMatch?.team_away_1stHalf_goals.toString()
            firstHalfTeam2.setTextColor(temp)
            secondHalfTeam1.text=currentMatch?.team_home_2ndHalf_goals.toString()
            secondHalfTeam1.setTextColor(temp)
            secondHalfTeam2.text=currentMatch?.team_away_2ndHalf_goals.toString()
            secondHalfTeam2.setTextColor(temp)

            findViewById<TextView>(R.id.textView27).setTextColor(temp)
            findViewById<TextView>(R.id.textView30).setTextColor(temp)
            findViewById<TextView>(R.id.dash).setTextColor(temp)
            findViewById<TextView>(R.id.dash2).setTextColor(temp)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.detailFragment, MatchFragment(currentMatch!!.Team1Name, currentMatch.Team1Score, currentMatch.Team1Photo, currentMatch.Team2Name,
                        currentMatch.Team2Score, currentMatch.Team2Photo, mainColor, secondColor, currentMatch.date))
                commit()
            }

        }



    }
}