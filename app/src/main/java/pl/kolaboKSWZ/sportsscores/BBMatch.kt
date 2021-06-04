package pl.kolaboKSWZ.sportsscores

data class BBMatch(
        val matchID: Int,
        val team1Name: String,
        val team1Score: String,
        val team1id: Int,

        val team2Name: String,
        val team2Score: String,
        val team2id: Int,

        val matchLocation: String,
        val date: String,

)
