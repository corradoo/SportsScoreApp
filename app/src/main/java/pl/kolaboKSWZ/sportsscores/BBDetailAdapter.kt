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
        holder.points.text="23"
        holder.minutes.text="34:20"
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