package pl.kolaboKSWZ.sportsscores

import android.content.res.Resources
import kotlin.random.Random

fun matchesList(resources: Resources) : List<Match>
{
    return listOf(
        Match(
            matchID= Random.nextInt(),
            seasonID=1,
            Team1Name="Hiszpania",
            Team1Photo=R.drawable.spain,
            Team1Score="3",
            Team2Name="Anglia",
            Team2Photo=R.drawable.england,
            Team2Score="2"
        )

    )
}