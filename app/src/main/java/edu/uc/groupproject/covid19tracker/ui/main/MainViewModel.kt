package edu.uc.groupproject.covid19tracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uc.groupproject.covid19tracker.dto.Cases
import edu.uc.groupproject.covid19tracker.dto.GlobalData
import edu.uc.groupproject.covid19tracker.dto.News
import edu.uc.groupproject.covid19tracker.service.CasesByCountryDataProvider
import edu.uc.groupproject.covid19tracker.service.GlobalDataProvider
import edu.uc.groupproject.covid19tracker.service.NewsDataProvider
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    var gmData: MutableLiveData<GlobalData> = MutableLiveData<GlobalData>()
    var cData: MutableLiveData<Cases> = MutableLiveData<Cases>()
    var newsCountry: String = "US"

    init {
        /**
         * fetchGlobalData() is not in use yet but will use in future
         */
        globalData()
    }

    private fun globalData() {
        GlobalScope.launch(context = Dispatchers.Main) {
            val gdp = GlobalDataProvider()

            /**
             * Call API to retrieve specific global covid19 data
             */
            val gCases: ArrayList<String>? = gdp.getGlobalCovidData("total_cases")
            val gActive: ArrayList<String>? = gdp.getGlobalCovidData("active_cases")
            val gTotalDeath: ArrayList<String>? = gdp.getGlobalCovidData("total_deaths")
            val gRecovered: ArrayList<String>? = gdp.getGlobalCovidData("total_recovered")
            val gNewCases: ArrayList<String>? = gdp.getGlobalCovidData("new_cases")
            val gNewDeaths: ArrayList<String>? = gdp.getGlobalCovidData("new_deaths")
            val gSeriousCritical: ArrayList<String>? = gdp.getGlobalCovidData("serious_critical")
            val gCasesPerMillion: ArrayList<String>? = gdp.getGlobalCovidData("total_cases_per_1m_population")
            val gDeathPerMillion: ArrayList<String>? = gdp.getGlobalCovidData("deaths_per_1m_population")
            val gStatistics: ArrayList<String>? = gdp.getGlobalCovidData("statistic_taken_at")
            delay(2000)


            /**
             * Set fetched data to DTO objects
             */
            val globalDataDto = GlobalData(
                gCases!![0], gActive!![0],
                gTotalDeath!![0], gRecovered!![0], gNewCases!![0],
                gNewDeaths!![0], gSeriousCritical!![0], gCasesPerMillion!![0],
                gDeathPerMillion!![0], gStatistics!![0]
            )

            gmData.value = globalDataDto

            val cbc = CasesByCountryDataProvider()

            /**
             * Call API to retrieve cases by country data
             */
            val country: ArrayList<String>? = cbc.getCasesByCountryData("country_name")
            val cases: ArrayList<String>? = cbc.getCasesByCountryData("cases")
            val deaths: ArrayList<String>? = cbc.getCasesByCountryData("deaths")
            val active: ArrayList<String>? = cbc.getCasesByCountryData("active_cases")
            val totalRecovered: ArrayList<String>? = cbc.getCasesByCountryData("total_recovered")
            val newDeaths: ArrayList<String>? = cbc.getCasesByCountryData("new_deaths")
            val newCases: ArrayList<String>? = cbc.getCasesByCountryData("new_cases")
            val seriousCritical: ArrayList<String>? = cbc.getCasesByCountryData("serious_critical")
            val totalCasesPerMillionPopulation: ArrayList<String>? = cbc.getCasesByCountryData("total_cases_per_1m_population")
            delay(2000)

            /**
             * Set fetched country data to DTO object
             */
            val casesDto = Cases(country!!, cases!!, deaths!!, active!!, totalRecovered!!, newDeaths!!,
                newCases!!, seriousCritical!!, totalCasesPerMillionPopulation!!)

            cData.value = casesDto

            /**
             * Call API to retrieve news data
             */
            val ndp = NewsDataProvider()

            val author: ArrayList<String>? = ndp.getNewsData(newsCountry, "author")
            val title: ArrayList<String>? = ndp.getNewsData(newsCountry, "title")
            val description: ArrayList<String>? = ndp.getNewsData(newsCountry, "description")
            val url: ArrayList<String>? = ndp.getNewsData(newsCountry, "url")
            val urlToImage: ArrayList<String>? = ndp.getNewsData(newsCountry, "urlToImage")
            val publishedAt: ArrayList<String>? = ndp.getNewsData(newsCountry, "publishedAt")
            val content: ArrayList<String>? = ndp.getNewsData(newsCountry, "content")
            delay(2000)

            /**
             * Set fetched news data to DTO object
             */
            val newsDto = News(author!!, title!!, description!!, url!!, urlToImage!!, publishedAt!!, content!!)
        }
    }


}
