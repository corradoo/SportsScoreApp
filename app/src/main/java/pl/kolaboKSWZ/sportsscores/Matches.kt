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
            Team1Photo="https://lh3.googleusercontent.com/proxy/FFqm26F5Ov3OBbgpBLy3NZLtnuRQCJGl5PfXxh1G-MDh5vXWtJ8KNfr5T8iDOSSBef2HRGJ0UFfbxZ5LrAvwC5ks4PgOM7GknmJOevgjqYiCmkIz",
            Team1Score="3",
            Team2Name="Anglia",
            Team2Photo="https://lh3.googleusercontent.com/proxy/FFqm26F5Ov3OBbgpBLy3NZLtnuRQCJGl5PfXxh1G-MDh5vXWtJ8KNfr5T8iDOSSBef2HRGJ0UFfbxZ5LrAvwC5ks4PgOM7GknmJOevgjqYiCmkIz",
            Team2Score="2"
        )

    )
}