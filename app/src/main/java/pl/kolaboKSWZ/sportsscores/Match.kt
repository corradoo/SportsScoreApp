package pl.kolaboKSWZ.sportsscores

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URL

@Entity(tableName="Matches")
data class Match(
        @PrimaryKey(autoGenerate = true) val matchID: Int,
        val seasonID: Int,

        val Team1Name: String,
        val Team1Photo: String,
        val Team1Score: String,

        val Team2Name:String,
        val Team2Photo: String,
        val Team2Score:String
)