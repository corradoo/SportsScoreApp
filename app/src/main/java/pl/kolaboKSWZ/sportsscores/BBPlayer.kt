package pl.kolaboKSWZ.sportsscores

data class BBPlayer(
        val playerID: Int,
        val playerFirstName: String,
        val playerLastName: String,
        val playerPosition: String,
        val playerTeamID: Int,
        val points : Int,
        val rbs : Int,
        val steals : Int,
        val assists : Int,
        val minutes : String,
)
