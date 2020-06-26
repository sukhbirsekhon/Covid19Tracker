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
    var casesService: CasesService = CasesService()

    init {
        fetchGlobalData()
        globalData()
    }

    fun fetchGlobalData() {
        /**
         * call cases service to fetch global covid19 data
         */
        globalData = casesService.fetchGlobalData()
        println(globalData)
    }

    fun globalData() {
        GlobalScope.launch(context = Dispatchers.Main) {
            var gdp : GlobalDataProvider = GlobalDataProvider()

            /**
             * Call API to retrieve specific global covid19 data
             */
            var gCases: ArrayList<String>? = gdp.getTotalCases()
            var gActive: ArrayList<String>? = gdp.getTotalActiveCases()
            var gTotalDeath: ArrayList<String>? = gdp.getTotalDeaths()
            var gRecovered: ArrayList<String>? = gdp.getTotalRecovered()
            var gNewCases: ArrayList<String>? = gdp.getTotalNewCases()
            var gNewDeaths: ArrayList<String>? = gdp.getTotalNewDeaths()
            var gSeriousCritical: ArrayList<String>? = gdp.getTotalSeriousCritical()
            var gCasesPerMillion: ArrayList<String>? = gdp.getTotalCasesPerMillionPopulation()
            var gDeathPerMillion: ArrayList<String>? = gdp.getTotalDeathsPerMillionPopulation()
            var gStatistics: ArrayList<String>? = gdp.getStatisticTakenTime()
            delay(2000)

            /**
             * Set fetched data to DTO objects
             */
            var globalDataDto = GlobalData(gCases!!.get(0), gActive!!.get(0),
                gTotalDeath!!.get(0), gRecovered!!.get(0), gNewCases!!.get(0),
                gNewDeaths!!.get(0), gSeriousCritical!!.get(0), gCasesPerMillion!!.get(0),
                gDeathPerMillion!!.get(0), gStatistics!!.get(0))

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

            var casesDto = Cases(country!!, cases!!, deaths!!, active!!, totalRecovered!!, newDeaths!!,
                newCases!!, seriousCritical!!, totalCasesPerMillionPopulation!!)

        }
    }


}
