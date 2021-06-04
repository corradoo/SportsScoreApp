package pl.kolaboKSWZ.sportsscores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


        when(matchesList[position].team1id){
            1->holder.team1Logo.setImageResource(R.drawable.atl_log)
            2->holder.team1Logo.setImageResource(R.drawable.bos_log)
            3->holder.team1Logo.setImageResource(R.drawable.bkn_log)
            4->holder.team1Logo.setImageResource(R.drawable.cha_log)
            5->holder.team1Logo.setImageResource(R.drawable.chi_log)
            6->holder.team1Logo.setImageResource(R.drawable.cle_log)
            7->holder.team1Logo.setImageResource(R.drawable.dal_log)
            8->holder.team1Logo.setImageResource(R.drawable.den_log)
            9->holder.team1Logo.setImageResource(R.drawable.det_log)
            10->holder.team1Logo.setImageResource(R.drawable.gsw_log)
            11->holder.team1Logo.setImageResource(R.drawable.hou_log)
            12->holder.team1Logo.setImageResource(R.drawable.ind_log)
            13->holder.team1Logo.setImageResource(R.drawable.lac_log)
            14->holder.team1Logo.setImageResource(R.drawable.lal_log)
            15->holder.team1Logo.setImageResource(R.drawable.mem_log)
            16->holder.team1Logo.setImageResource(R.drawable.mia_log)
            17->holder.team1Logo.setImageResource(R.drawable.mil_log)
            18->holder.team1Logo.setImageResource(R.drawable.min_log)
            19->holder.team1Logo.setImageResource(R.drawable.nop_log)
            20->holder.team1Logo.setImageResource(R.drawable.nyk_log)
            21->holder.team1Logo.setImageResource(R.drawable.okc_log)
            22->holder.team1Logo.setImageResource(R.drawable.orl_log)
            23->holder.team1Logo.setImageResource(R.drawable.phi_log)
            24->holder.team1Logo.setImageResource(R.drawable.phx_log)
            25->holder.team1Logo.setImageResource(R.drawable.por_log)
            26->holder.team1Logo.setImageResource(R.drawable.sac_log)
            27->holder.team1Logo.setImageResource(R.drawable.sas_log)
            28->holder.team1Logo.setImageResource(R.drawable.tor_log)
            29->holder.team1Logo.setImageResource(R.drawable.uta_log)
            30->holder.team1Logo.setImageResource(R.drawable.was_log)
        }

        when(matchesList[position].team2id){
            1->holder.team2Logo.setImageResource(R.drawable.atl_log)
            2->holder.team2Logo.setImageResource(R.drawable.bos_log)
            3->holder.team2Logo.setImageResource(R.drawable.bkn_log)
            4->holder.team2Logo.setImageResource(R.drawable.cha_log)
            5->holder.team2Logo.setImageResource(R.drawable.chi_log)
            6->holder.team2Logo.setImageResource(R.drawable.cle_log)
            7->holder.team2Logo.setImageResource(R.drawable.dal_log)
            8->holder.team2Logo.setImageResource(R.drawable.den_log)
            9->holder.team2Logo.setImageResource(R.drawable.det_log)
            10->holder.team2Logo.setImageResource(R.drawable.gsw_log)
            11->holder.team2Logo.setImageResource(R.drawable.hou_log)
            12->holder.team2Logo.setImageResource(R.drawable.ind_log)
            13->holder.team2Logo.setImageResource(R.drawable.lac_log)
            14->holder.team2Logo.setImageResource(R.drawable.lal_log)
            15->holder.team2Logo.setImageResource(R.drawable.mem_log)
            16->holder.team2Logo.setImageResource(R.drawable.mia_log)
            17->holder.team2Logo.setImageResource(R.drawable.mil_log)
            18->holder.team2Logo.setImageResource(R.drawable.min_log)
            19->holder.team2Logo.setImageResource(R.drawable.nop_log)
            20->holder.team2Logo.setImageResource(R.drawable.nyk_log)
            21->holder.team2Logo.setImageResource(R.drawable.okc_log)
            22->holder.team2Logo.setImageResource(R.drawable.orl_log)
            23->holder.team2Logo.setImageResource(R.drawable.phi_log)
            24->holder.team2Logo.setImageResource(R.drawable.phx_log)
            25->holder.team2Logo.setImageResource(R.drawable.por_log)
            26->holder.team2Logo.setImageResource(R.drawable.sac_log)
            27->holder.team2Logo.setImageResource(R.drawable.sas_log)
            28->holder.team2Logo.setImageResource(R.drawable.tor_log)
            29->holder.team2Logo.setImageResource(R.drawable.uta_log)
            30->holder.team2Logo.setImageResource(R.drawable.was_log)
        }

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