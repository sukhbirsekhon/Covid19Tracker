package edu.uc.groupproject.covid19tracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uc.groupproject.covid19tracker.dto.Cases
import edu.uc.groupproject.covid19tracker.dto.GlobalData
import edu.uc.groupproject.covid19tracker.service.CasesByCountryDataProvider
import edu.uc.groupproject.covid19tracker.service.CasesService
import edu.uc.groupproject.covid19tracker.service.GlobalDataProvider
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    var globalData: MutableLiveData<ArrayList<GlobalData>> = MutableLiveData<ArrayList<GlobalData>>()
    var gmData: MutableLiveData<GlobalData> = MutableLiveData<GlobalData>()
    var cData: MutableLiveData<Cases> = MutableLiveData<Cases>()
    var casesService: CasesService = CasesService()

    init {
        /**
         * fetchGlobalData() is not in use yet but will use in future
         */
        fetchGlobalData()
        globalData()
    }

    fun fetchGlobalData() {
        /**
         * all cases service to fetch global covid19 data
         */
        globalData = casesService.fetchGlobalData()
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
            var globalDataDto = GlobalData()
            try {
                globalDataDto = GlobalData(gCases!!.get(0), gActive!!.get(0),
                    gTotalDeath!!.get(0), gRecovered!!.get(0), gNewCases!!.get(0),
                    gNewDeaths!!.get(0), gSeriousCritical!!.get(0), gCasesPerMillion!!.get(0),
                    gDeathPerMillion!!.get(0), gStatistics!!.get(0))
            } catch(e: java.lang.NullPointerException) {
                e.printStackTrace()
            }

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
        }
    }


}
