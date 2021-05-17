package pl.kolaboKSWZ.sportsscores

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Matches")
data class Match(
        @PrimaryKey(autoGenerate = true) val matchID: Int,
        val seasonID: Int,
        val Team1Name: String,
        @DrawableRes
        val Team1Photo: Int,
        val Team1Score: String,
        val Team2Name:String,
        @DrawableRes
        val Team2Photo: Int,
        val Team2Score:String
)