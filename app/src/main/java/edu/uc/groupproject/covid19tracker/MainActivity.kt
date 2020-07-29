package edu.uc.groupproject.covid19tracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.uc.groupproject.covid19tracker.ui.main.ByCountryFragment
import edu.uc.groupproject.covid19tracker.ui.main.MainFragment
import edu.uc.groupproject.covid19tracker.ui.main.NewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment
    private lateinit var byCountryFragment: ByCountryFragment
    private lateinit var newsFragment: NewsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Link the bottom navigation to the different fragments
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                // Link the home fragment to the home navigation button
                R.id.home -> {
                    mainFragment = MainFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, mainFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                // Link the cases by country fragment to the cases by country navigation button
                R.id.cases_by_country -> {
                    byCountryFragment = ByCountryFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, byCountryFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                // Link the news fragment to the news navigation button
                R.id.news -> {
                    newsFragment = NewsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, newsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }
}
