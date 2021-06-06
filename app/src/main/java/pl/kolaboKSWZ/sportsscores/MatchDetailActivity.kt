package pl.kolaboKSWZ.sportsscores

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class MatchDetailActivity : AppCompatActivity() {

    private val matchDetailViewModel by viewModels<MatchDetailViewModel>
    {
        MatchDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        var currentMatchId: Int? = null
        val team1photo: ImageView=findViewById(R.id.team1photoDetail)
        val team2photo: ImageView=findViewById(R.id.team2photoDetail)
        val team1name: TextView =findViewById(R.id.team1nameDetail)
        val team2name: TextView=findViewById(R.id.team2nameDetail)
        val team1score: TextView=findViewById(R.id.team1scoreDetail)
        val team2score: TextView=findViewById(R.id.team2scoreDetail)
        val venue: TextView=findViewById(R.id.venueDetail)
        val date:TextView=findViewById(R.id.dateDetail)
        val firstHalfTeam1: TextView=findViewById(R.id.firstHalfTeam1)
        val firstHalfTeam2: TextView=findViewById(R.id.firstHalfTeam2)
        val secondHalfTeam1: TextView=findViewById(R.id.secondHalfTeam1)
        val secondHalfTeam2: TextView=findViewById(R.id.secondHalfTeam2)
        val bundle: Bundle?=intent.extras

        if(bundle!=null)
        {
            currentMatchId=bundle.getInt(MATCH_ID)
        }

        currentMatchId?.let{
            val currentMatch=matchDetailViewModel.getMatchForId(it)
            System.out.println(currentMatch?.matchID)
            Picasso.get().load(currentMatch?.Team1Photo).into(team1photo)
            Picasso.get().load(currentMatch?.Team2Photo).into(team2photo)
            team1name.text=currentMatch?.Team1Name
            team2name.text=currentMatch?.Team2Name
            team1score.text=currentMatch?.Team1Score
            team2score.text=currentMatch?.Team2Score
            venue.text=currentMatch?.venue
            date.text=currentMatch?.date
            firstHalfTeam1.text=currentMatch?.team_home_1stHalf_goals.toString()
            firstHalfTeam2.text=currentMatch?.team_away_1stHalf_goals.toString()
            secondHalfTeam1.text=currentMatch?.team_home_2ndHalf_goals.toString()
            secondHalfTeam2.text=currentMatch?.team_away_2ndHalf_goals.toString()

        }



    }
}