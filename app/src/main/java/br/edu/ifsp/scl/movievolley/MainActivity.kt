package br.edu.ifsp.scl.movievolley

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.buttonSearch
import kotlinx.android.synthetic.main.content_main.editTextSearch

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var searchType = SearchType.TITLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        buttonSearch.setOnClickListener {
            SearchAsyncTask(searchType).apply {
                attachCallback(object : SearchCallback {
                    override fun onSuccess(movieResponse: MovieResponse) {
                        val beginTransaction = supportFragmentManager.beginTransaction()
                        beginTransaction.add(R.id.containerPoster,
                                PosterFragment.newInstance(movieResponse.poster))
                        beginTransaction.commit()
                    }

                    override fun onError(e: Exception) {

                    }

                })
            }.execute(editTextSearch.text.toString())
        }
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
            }
            R.id.navSearchById -> {
                searchType = SearchType.ID
                editTextSearch.setText("")
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    enum class SearchType {
        TITLE, ID
    }
}
