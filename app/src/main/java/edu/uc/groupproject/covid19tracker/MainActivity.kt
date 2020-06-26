package edu.uc.groupproject.covid19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uc.groupproject.covid19tracker.ui.main.MainFragment
import edu.uc.groupproject.covid19tracker.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
