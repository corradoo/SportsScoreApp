package pl.kolaboKSWZ.sportsscores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BBMatchesAdapter(
        private val matchesList: ArrayList<BBMatch>,
        private val listener:OnItemClickListener
        ):
    RecyclerView.Adapter<BBMatchesAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view),
    View.OnClickListener{
        val score: TextView=view.findViewById(R.id.bb_match_score)
        val team1Logo: ImageView= view.findViewById(R.id.bb_team1)
        val team2Logo: ImageView= view.findViewById(R.id.bb_team2)
        val matchLoc: TextView= view.findViewById(R.id.bb_match_location)
        val matchDate: TextView=view.findViewById(R.id.bb_match_date)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position= bindingAdapterPosition
            if(position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BBMatchesAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.basketball_match_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.matchLoc.text="@"+matchesList[position].matchLocation
        holder.score.text=matchesList[position].team1Score+"-"+matchesList[position].team2Score
        holder.team1Logo.setImageResource(R.drawable.lal_log)
        holder.team2Logo.setImageResource(R.drawable.gsw_log)
        holder.matchDate.text=matchesList[position].date
    }

    override fun getItemCount(): Int {
        return matchesList.size
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}