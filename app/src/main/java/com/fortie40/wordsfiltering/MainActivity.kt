package com.fortie40.wordsfiltering

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var adapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNames()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = getString(R.string.search_name)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchName(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchName(newText)
                return false
            }
        })
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.isIconified = true
        } else {
            super.onBackPressed()
        }
    }

    private fun getNames() {
        val names: List<String> = arrayListOf(
            "Fortie40", "Java", "Kotlin", "C++", "PHP", "Javascript", "Objective-C", "Swift",
            "Groovy", "Haskell", "JQuery", "KRYPTON", "LotusScript", "Mortran", "NewLISP", "Orwell",
            "Hopscotch", "JScript", "AngelScript", "Bash", "Clojure", "C", "COBOL", "CSS",
            "Cybil", "Pascal", "Perl", "Smalltalk", "SQL", "Unicon", "Ubercode", "Fortran",
            "Hollywood", "SMALL", "Lisp", "PureScript", "R++", "XQuery", "YAML", "ZOPL"
        )

        adapter = MainActivityAdapter(names)
        names_item.adapter = adapter
    }

    private fun searchName(p0: String?) {
        search_progress.visibility = View.VISIBLE
        adapter.string = p0
        adapter.filter.filter(p0!!.toLowerCase(Locale.getDefault())) {
            when(adapter.itemCount) {
                0 -> {
                    no_results_found.visibility = View.VISIBLE
                    no_results_found.text = getString(R.string.no_results_found, p0)
                }
                else -> {
                    no_results_found.visibility = View.GONE
                }
            }
            Log.i("MainActivityA", adapter.string!!)
            Log.i("MainActivity", p0)
            if (adapter.string == "" || adapter.string == p0) {
                Log.i("MainActivity", "done")
                search_progress.visibility = View.GONE
            }
        }
    }
}