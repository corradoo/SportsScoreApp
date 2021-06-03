package pl.kolaboKSWZ.sportsscores

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchAPI {
    @GET("/seasons/{league}/fixtures?expand=away_team,home_team&from={date}")
    fun getLeagueFromDate(@Path("league") league : String ,@Path("date") date : String) : Call<Match>
}