package edu.uc.groupproject.covid19tracker.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uc.groupproject.covid19tracker.dto.Cases
import edu.uc.groupproject.covid19tracker.dto.GlobalData
import edu.uc.groupproject.covid19tracker.dto.News
import edu.uc.groupproject.covid19tracker.service.CasesByCountryDataProvider
import edu.uc.groupproject.covid19tracker.service.GlobalDataProvider
import edu.uc.groupproject.covid19tracker.service.NewsDataProvider
import edu.uc.groupproject.covid19tracker.service.StateDataProvider
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    var gmData: MutableLiveData<GlobalData> = MutableLiveData<GlobalData>()
    var cData: MutableLiveData<Cases> = MutableLiveData<Cases>()
    var nData: MutableLiveData<News> = MutableLiveData<News>()

    init {
        /**
         * fetchGlobalData() is not in use yet but will use in future
         */
        globalData()
    }

    fun globalData() {
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

            val casesByCountry = CasesByCountryDataProvider()

            /**
             * Call API to retrieve cases by country data
             */
            val country: ArrayList<String>? = casesByCountry.getCasesByCountryData("country_name")
            val cases: ArrayList<String>? = casesByCountry.getCasesByCountryData("cases")
            val deaths: ArrayList<String>? = casesByCountry.getCasesByCountryData("deaths")
            val active: ArrayList<String>? = casesByCountry.getCasesByCountryData("active_cases")
            val totalRecovered: ArrayList<String>? = casesByCountry.getCasesByCountryData("total_recovered")
            val newDeaths: ArrayList<String>? = casesByCountry.getCasesByCountryData("new_deaths")
            val newCases: ArrayList<String>? = casesByCountry.getCasesByCountryData("new_cases")
            val seriousCritical: ArrayList<String>? = casesByCountry.getCasesByCountryData("serious_critical")
            val totalCasesPerMillionPopulation: ArrayList<String>? = casesByCountry.getCasesByCountryData("total_cases_per_1m_population")
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
            val newsData = NewsDataProvider()

            val author: ArrayList<String>? = newsData.getNewsData("author")
            val title: ArrayList<String>? = newsData.getNewsData("title")
            val description: ArrayList<String>? = newsData.getNewsData("description")
            val url: ArrayList<String>? = newsData.getNewsData("url")
            val urlToImage: ArrayList<String>? = newsData.getNewsData("urlToImage")
            val publishedAt: ArrayList<String>? = newsData.getNewsData("publishedAt")
            val content: ArrayList<String>? = newsData.getNewsData("content")
            delay(2000)

            /**
             * Set fetched news data to DTO object
             */
            val newsDto = News(author!!, title!!, description!!, url!!, urlToImage!!, publishedAt!!, content!!)

            nData.value = newsDto
        }
    }


}
