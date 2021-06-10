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
        when(playerList[position].playerTeamID){
            1->holder.teamLogo.setImageResource(R.drawable.atl_log)
            2->holder.teamLogo.setImageResource(R.drawable.bos_log)
            3->holder.teamLogo.setImageResource(R.drawable.bkn_log)
            4->holder.teamLogo.setImageResource(R.drawable.cha_log)
            5->holder.teamLogo.setImageResource(R.drawable.chi_log)
            6->holder.teamLogo.setImageResource(R.drawable.cle_log)
            7->holder.teamLogo.setImageResource(R.drawable.dal_log)
            8->holder.teamLogo.setImageResource(R.drawable.den_log)
            9->holder.teamLogo.setImageResource(R.drawable.det_log)
            10->holder.teamLogo.setImageResource(R.drawable.gsw_log)
            11->holder.teamLogo.setImageResource(R.drawable.hou_log)
            12->holder.teamLogo.setImageResource(R.drawable.ind_log)
            13->holder.teamLogo.setImageResource(R.drawable.lac_log)
            14->holder.teamLogo.setImageResource(R.drawable.lal_log)
            15->holder.teamLogo.setImageResource(R.drawable.mem_log)
            16->holder.teamLogo.setImageResource(R.drawable.mia_log)
            17->holder.teamLogo.setImageResource(R.drawable.mil_log)
            18->holder.teamLogo.setImageResource(R.drawable.min_log)
            19->holder.teamLogo.setImageResource(R.drawable.nop_log)
            20->holder.teamLogo.setImageResource(R.drawable.nyk_log)
            21->holder.teamLogo.setImageResource(R.drawable.okc_log)
            22->holder.teamLogo.setImageResource(R.drawable.orl_log)
            23->holder.teamLogo.setImageResource(R.drawable.phi_log)
            24->holder.teamLogo.setImageResource(R.drawable.phx_log)
            25->holder.teamLogo.setImageResource(R.drawable.por_log)
            26->holder.teamLogo.setImageResource(R.drawable.sac_log)
            27->holder.teamLogo.setImageResource(R.drawable.sas_log)
            28->holder.teamLogo.setImageResource(R.drawable.tor_log)
            29->holder.teamLogo.setImageResource(R.drawable.uta_log)
            30->holder.teamLogo.setImageResource(R.drawable.was_log)
        }

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

}