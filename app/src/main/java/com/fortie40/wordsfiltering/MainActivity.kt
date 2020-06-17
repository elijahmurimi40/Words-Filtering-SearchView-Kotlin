package com.fortie40.wordsfiltering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNames()
    }

    private fun getNames() {
        val names: List<String> = arrayListOf(
            "Fortie40", "Java", "Kotlin", "C++", "PHP", "Javascript", "Objective-C", "Swift",
            "Groovy", "Haskell", "JQuery", "KRYPTON", "LotusScript", "Mortran", "NewLISP", "Orwell",
            "Hopscotch", "JScript", "AngelScript", "Bash", "Clojure", "C", "COBOL", "CSS",
            "Cybil", "Pascal", "Perl", "Smalltalk", "SQL", "Unicon", "Ubercode", "Fortran",
            "Hollywood", "SMALL", "Lisp", "PureScript", "R++", "XQuery", "YAML", "ZOPL"
        )

        val adapter = MainActivityAdapter(names)
        names_item.adapter = adapter
    }
}