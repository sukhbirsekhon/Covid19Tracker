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
            var gdp : GlobalDataProvider = GlobalDataProvider()

            /**
             * Call API to retrieve specific global covid19 data
             */
            var gCases: ArrayList<String>? = gdp.getGlobalCovidData("total_cases")
            var gActive: ArrayList<String>? = gdp.getGlobalCovidData("active_cases")
            var gTotalDeath: ArrayList<String>? = gdp.getGlobalCovidData("total_deaths")
            var gRecovered: ArrayList<String>? = gdp.getGlobalCovidData("total_recovered")
            var gNewCases: ArrayList<String>? = gdp.getGlobalCovidData("new_cases")
            var gNewDeaths: ArrayList<String>? = gdp.getGlobalCovidData("new_deaths")
            var gSeriousCritical: ArrayList<String>? = gdp.getGlobalCovidData("serious_critical")
            var gCasesPerMillion: ArrayList<String>? = gdp.getGlobalCovidData("total_cases_per_1m_population")
            var gDeathPerMillion: ArrayList<String>? = gdp.getGlobalCovidData("deaths_per_1m_population")
            var gStatistics: ArrayList<String>? = gdp.getGlobalCovidData("statistic_taken_at")
            delay(2000)


            /**
             * Set fetched data to DTO objects
             */
            var globalDataDto = GlobalData(gCases!!.get(0), gActive!!.get(0),
                gTotalDeath!!.get(0), gRecovered!!.get(0), gNewCases!!.get(0),
                gNewDeaths!!.get(0), gSeriousCritical!!.get(0), gCasesPerMillion!!.get(0),
                gDeathPerMillion!!.get(0), gStatistics!!.get(0))

            gmData.value = globalDataDto

            var cbc : CasesByCountryDataProvider = CasesByCountryDataProvider()

            /**
             * Call API to retrieve cases by country data
             */
            var country: ArrayList<String>? = cbc.getCasesByCountryData("country_name")
            var cases: ArrayList<String>? = cbc.getCasesByCountryData("cases")
            var deaths: ArrayList<String>? = cbc.getCasesByCountryData("deaths")
            var active: ArrayList<String>? = cbc.getCasesByCountryData("active_cases")
            var totalRecovered: ArrayList<String>? = cbc.getCasesByCountryData("total_recovered")
            var newDeaths: ArrayList<String>? = cbc.getCasesByCountryData("new_deaths")
            var newCases: ArrayList<String>? = cbc.getCasesByCountryData("new_cases")
            var seriousCritical: ArrayList<String>? = cbc.getCasesByCountryData("serious_critical")
            var totalCasesPerMillionPopulation: ArrayList<String>? = cbc.getCasesByCountryData("total_cases_per_1m_population")
            delay(2000)

            /**
             * Set fetched country data to DTO object
             */
            var casesDto = Cases(country!!, cases!!, deaths!!, active!!, totalRecovered!!, newDeaths!!,
                newCases!!, seriousCritical!!, totalCasesPerMillionPopulation!!)

            cData.value = casesDto
        }
    }


}
