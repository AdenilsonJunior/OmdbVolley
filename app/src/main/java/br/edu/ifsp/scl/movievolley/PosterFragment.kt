package br.edu.ifsp.scl.movievolley

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_poster.imageViewPoster
import java.text.ParseException

class PosterFragment : Fragment(), BindableFragment {

    companion object {
        fun newInstance(): Fragment {
            return PosterFragment()
        }
        const val TAG = "PosterFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_poster, container, false)
    }

    override fun <TYPE> bind(model: TYPE){
        when (model) {
            is String -> {
                view?.run {
                    Glide.with(this).load(model).into(imageViewPoster)
                }
            }
            else -> throw RuntimeException("This method expected a String")
        }

    }
}