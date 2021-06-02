package pl.kolaboKSWZ.sportsscores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MatchesAdapter(private val onClick: (Match) -> Unit, private val longClick: (Match) -> Unit) : ListAdapter<Match, MatchesAdapter.MatchViewHolder>(TaskDiffCallback)
{
    class MatchViewHolder(itemView: View, val onClick: (Match)->Unit, val longClick: (Match) -> Unit) : RecyclerView.ViewHolder(itemView)
    {
        private val Team1Score: TextView = itemView.findViewById(R.id.team1Score)
        private val Team1Photo: ImageView = itemView.findViewById(R.id.team1Photo)
        private val Team2Score: TextView = itemView.findViewById(R.id.team2Score)
        private val Team2Photo: ImageView = itemView.findViewById(R.id.team2Photo)
        private var currentMatch: Match? = null
        init{
            itemView.setOnClickListener{
                currentMatch?.let{
                    onClick(it)
                }
            }
            itemView.setOnLongClickListener {
                currentMatch?.let{
                    longClick(it)
                }
                true
            }
        }

        fun bind(match:Match)
        {
            currentMatch = match
            Team1Score.text = match.Team1Score
            Team2Score.text = match.Team2Score
            Picasso.get().load(match.Team1Photo).into(Team1Photo)
            Picasso.get().load(match.Team2Photo).into(Team2Photo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):MatchViewHolder{
        val view= LayoutInflater.from(parent.context).inflate(R.layout.match_item,parent,false)
        return MatchViewHolder(view, onClick, longClick)
    }

    override fun onBindViewHolder(holder:MatchViewHolder,position:Int)
    {
        val match=getItem(position)
        holder.bind(match)
    }
}


object TaskDiffCallback : DiffUtil.ItemCallback<Match>()
{
    override fun areItemsTheSame(oldItem:Match, newItem:Match):Boolean
    {
        return oldItem==newItem
    }
    override fun areContentsTheSame(oldItem:Match,newItem:Match):Boolean{
        return oldItem.matchID==newItem.matchID
    }
}