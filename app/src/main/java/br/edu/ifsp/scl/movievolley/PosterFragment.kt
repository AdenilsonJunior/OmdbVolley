package br.edu.ifsp.scl.movievolley

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_poster.imageViewPoster

class PosterFragment : Fragment() {

    companion object {
       private const val KEY_POSTER = "key_poster"

        fun newInstance(urlPoster: String): Fragment {
            val bundle = Bundle()
            bundle.putString(KEY_POSTER, urlPoster)

            return PosterFragment().apply {
                this.arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_poster, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(KEY_POSTER)

        Glide.with(view).load(url).into(imageViewPoster)
    }

}