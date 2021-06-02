package pl.kolaboKSWZ.sportsscores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class TestFragment(private val team1Name: String, private val team1Score: String, private val team1Photo: String,
                   private val team2Name: String, private val team2Score: String, private val team2Photo: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.team1Name).text = team1Name
        view.findViewById<TextView>(R.id.team1Score).text = team1Score
        view.findViewById<TextView>(R.id.team2Name).text = team2Name
        view.findViewById<TextView>(R.id.team2Score).text = team2Score

        Picasso.get().load(team1Photo).into(view.findViewById<ImageView>(R.id.team1Photo))
        Picasso.get().load(team2Photo).into(view.findViewById<ImageView>(R.id.team2Photo))

    }
}