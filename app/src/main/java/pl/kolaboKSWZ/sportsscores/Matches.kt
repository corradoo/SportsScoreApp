package pl.kolaboKSWZ.sportsscores

import android.content.res.Resources

fun matchesList(resources: Resources) : List<Match>
{
    return listOf(
        Match(
            matchID=1,
            Team1Name="Hiszpania",
            Team1Photo=R.drawable.spain,
            Team1Score="3",
            Team2Name="Anglia",
            Team2Photo=R.drawable.england,
            Team2Score="2"
        ),
        Match(
            matchID=2,
            Team1Name="Niemcy",
            Team1Photo=R.drawable.germany,
            Team1Score="0",
            Team2Name="Włochy",
            Team2Photo=R.drawable.italy,
            Team2Score="2"
        ),
        Match(
            matchID=3,
            Team1Name="Hiszpania",
            Team1Photo=R.drawable.spain,
            Team1Score="2",
            Team2Name="Włochy",
            Team2Photo=R.drawable.italy,
            Team2Score="2"
        ),
        Match(
            matchID=4,
            Team1Name="Hiszpania",
            Team1Photo=R.drawable.spain,
            Team1Score="3",
            Team2Name="Anglia",
            Team2Photo=R.drawable.england,
            Team2Score="2"
        ),
        Match(
            matchID=5,
            Team1Name="Niemcy",
            Team1Photo=R.drawable.germany,
            Team1Score="0",
            Team2Name="Włochy",
            Team2Photo=R.drawable.italy,
            Team2Score="2"
        ),
        Match(
            matchID=6,
            Team1Name="Anglia",
            Team1Photo=R.drawable.england,
            Team1Score="2",
            Team2Name="Włochy",
            Team2Photo=R.drawable.italy,
            Team2Score="2"
        ),

    )
}