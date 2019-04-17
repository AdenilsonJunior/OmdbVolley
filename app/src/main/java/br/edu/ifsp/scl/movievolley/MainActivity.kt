package br.edu.ifsp.scl.movievolley

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.buttonSearch
import kotlinx.android.synthetic.main.content_main.containerDetails
import kotlinx.android.synthetic.main.content_main.containerPoster
import kotlinx.android.synthetic.main.content_main.editTextSearch
import kotlinx.android.synthetic.main.content_main.textViewMovieNotFound

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var searchType = SearchType.TITLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navView.setCheckedItem(R.id.navSearchByTitle)

        addFragments()

        buttonSearch.setOnClickListener {
            SearchAsyncTask(searchType).apply {
                attachCallback(object : SearchCallback {
                    override fun onSuccess(movieResponse: MovieResponse) {
                        bindBindableFragments(movieResponse)
                        textViewMovieNotFound.visibility = View.GONE
                    }

                    override fun onError(e: Exception) {
                        Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                        textViewMovieNotFound.visibility = View.VISIBLE
                    }

                    override fun onMovieNotFound() {
                        editTextSearch.setText("")
                        textViewMovieNotFound.visibility = View.VISIBLE
                    }
                })
            }.execute(editTextSearch.text.toString())
        }
    }

    fun bindBindableFragments(movieResponse: MovieResponse) {
        val posterFragment = supportFragmentManager.findFragmentByTag(PosterFragment.TAG)
        val detailsFragment = supportFragmentManager.findFragmentByTag(DetailsFragment.TAG)

        if (posterFragment is BindableFragment) {
            posterFragment.bind(movieResponse.poster)
        }
        if (detailsFragment is BindableFragment) {
            detailsFragment.bind(movieResponse)
        }

        containerPoster.visibility = View.VISIBLE
        containerDetails.visibility = View.VISIBLE
    }

    private fun addFragments() {
        val beginTransaction = supportFragmentManager.beginTransaction()

        beginTransaction.add(R.id.containerPoster,
                PosterFragment.newInstance(), PosterFragment.TAG)

        beginTransaction.add(R.id.containerDetails,
                DetailsFragment.newInstance(), DetailsFragment.TAG)
        beginTransaction.commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navSearchByTitle -> {
                searchType = SearchType.TITLE
                editTextSearch.setText("")
                editTextSearch.setHint(R.string.hint_search_title)
            }
            R.id.navSearchById -> {
                searchType = SearchType.ID
                editTextSearch.setText("")
                editTextSearch.setHint(R.string.hint_search_by_id)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    enum class SearchType {
        TITLE, ID
    }
}
