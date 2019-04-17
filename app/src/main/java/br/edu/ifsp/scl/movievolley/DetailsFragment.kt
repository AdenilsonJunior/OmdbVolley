package br.edu.ifsp.scl.movievolley

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_details.labelAwards
import kotlinx.android.synthetic.main.fragment_details.textViewActors
import kotlinx.android.synthetic.main.fragment_details.textViewAwards
import kotlinx.android.synthetic.main.fragment_details.textViewDirector
import kotlinx.android.synthetic.main.fragment_details.textViewLanguageAndCountry
import kotlinx.android.synthetic.main.fragment_details.textViewProduction
import kotlinx.android.synthetic.main.fragment_details.textViewReleased
import kotlinx.android.synthetic.main.fragment_details.textViewTitleAndYear
import kotlinx.android.synthetic.main.fragment_details.textViewType
import kotlinx.android.synthetic.main.fragment_details.textViewWebsite

class DetailsFragment : Fragment(), BindableFragment {

    companion object {
        fun newInstance(): Fragment {
            return DetailsFragment()
        }

        const val TAG = "DetailsFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun <TYPE> bind(model: TYPE) {
        when (model){
            is MovieResponse -> {
                model.run<MovieResponse, Unit> {
                    textViewTitleAndYear.text = getString(R.string.title_year_movie, title, year)
                    textViewType.text = type
                    textViewReleased.text = released
                    textViewDirector.text = getString(R.string.text_directed_by, director)
                    textViewActors.text = getString(R.string.text_actors, actors)
                    textViewLanguageAndCountry.text = getString(R.string.text_language_country, idiom, country)
                    labelAwards.text = getString(R.string.text_awards)
                    textViewAwards.text = if (!awards.isBlank()) awards else getString(R.string.text_none)
                    textViewProduction.text = getString(R.string.text_production, production)
                    textViewWebsite.text = website
                }
            }
            else -> throw RuntimeException("This method expected a MovieResponse")
        }

    }


}