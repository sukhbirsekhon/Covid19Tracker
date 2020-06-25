package edu.uc.groupproject.covid19tracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uc.groupproject.covid19tracker.dto.GlobalData
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
        // call cases service to fetch global covid19 data
        globalData = casesService.fetchGlobalData()
        println(globalData)
    }

    fun globalData() {
        GlobalScope.launch(context = Dispatchers.Main) {
            var gdp : GlobalDataProvider = GlobalDataProvider()

            /**
             * Call API to retrieve specific global covid19 data
             */
            var cases: ArrayList<String>? = gdp.getTotalCases()
            var active: ArrayList<String>? = gdp.getTotalActiveCases()
            var totalDeath: ArrayList<String>? = gdp.getTotalDeaths()
            var recovered: ArrayList<String>? = gdp.getTotalRecovered()
            var newCases: ArrayList<String>? = gdp.getTotalNewCases()
            var newDeaths: ArrayList<String>? = gdp.getTotalNewDeaths()
            var seriousCritical: ArrayList<String>? = gdp.getTotalSeriousCritical()
            var casesPerMillion: ArrayList<String>? = gdp.getTotalCasesPerMillionPopulation()
            var deathPerMillion: ArrayList<String>? = gdp.getTotalDeathsPerMillionPopulation()
            var statistics: ArrayList<String>? = gdp.getStatisticTakenTime()
            delay(2000)

            /**
             * Set fetched data to DTO objects
             */
            var g = GlobalData(cases!!.get(0), active!!.get(0),
                totalDeath!!.get(0), recovered!!.get(0), newCases!!.get(0),
                newDeaths!!.get(0), seriousCritical!!.get(0), casesPerMillion!!.get(0),
                deathPerMillion!!.get(0), statistics!!.get(0))
        }
    }


}
