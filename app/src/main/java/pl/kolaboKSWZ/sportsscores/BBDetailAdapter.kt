package pl.kolaboKSWZ.sportsscores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BBDetailAdapter(private val playerList: ArrayList<BBPlayer>):
        RecyclerView.Adapter<BBDetailAdapter.ViewHolder>(){

            class ViewHolder(view: View): RecyclerView.ViewHolder(view),
            View.OnClickListener{
                val name: TextView= view.findViewById(R.id.detail_player_name)
                val points: TextView= view.findViewById(R.id.detail_player_points)
                val minutes: TextView= view.findViewById(R.id.detail_player_minutes)
                val rbs: TextView= view.findViewById(R.id.detail_player_rebounds)
                val ast: TextView= view.findViewById(R.id.detail_player_assists)
                val stl: TextView= view.findViewById(R.id.detail_player_steals)
                val teamLogo: ImageView= view.findViewById(R.id.detail_team_logo)
                override fun onClick(v: View?) {
                    TODO("Not yet implemented")
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.basketball_match_detail_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=playerList[position].playerFirstName+" "+playerList[position].playerLastName
        holder.points.text=playerList[position].points.toString()
        holder.minutes.text=playerList[position].minutes
        holder.rbs.text=playerList[position].rbs.toString()
        holder.ast.text=playerList[position].assists.toString()
        holder.stl.text=playerList[position].steals.toString()
        holder.teamLogo.setImageResource(R.drawable.gsw_log)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
//
//    interface OnItemClickListener{
//        fun onItemClick(position: Int)
//    }
}