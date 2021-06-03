package pl.kolaboKSWZ.sportsscores

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName="Matches")
data class Match(
        @PrimaryKey(autoGenerate = true) val matchID: Int,
        val seasonID: Int,
        val venue : String,
        val date: String,

        val Team1Name: String,
        val Team1Photo: String,
        val Team1Score: String,
        val team_home_1stHalf_goals : Int,
        val team_home_2ndHalf_goals : Int,

        val Team2Name:String,
        val Team2Photo: String,
        val Team2Score:String,
        val team_away_1stHalf_goals : Int,
        val team_away_2ndHalf_goals : Int,
)