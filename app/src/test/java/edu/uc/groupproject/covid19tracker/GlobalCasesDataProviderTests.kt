package edu.uc.groupproject.covid19tracker

import edu.uc.groupproject.covid19tracker.service.GlobalDataProvider
import org.junit.Assert.*
import org.junit.Test

/**
 * Tests confirming data is being received from Global Cases API
 */
class GlobalCasesDataProviderTests {

    @Test
    fun retrieveCasesByGlobalApiDataTest_totalCases() {
        var globalCaseData = GlobalDataProvider()
        val totalCases: ArrayList<String>? = globalCaseData.getGlobalCovidData("total_cases")
        Thread.sleep(2000)
        assertNotEquals(totalCases!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_activeCases() {
        var globalCaseData = GlobalDataProvider()
        val activeCases: ArrayList<String>? = globalCaseData.getGlobalCovidData("active_cases")
        Thread.sleep(2000)
        assertNotEquals(activeCases!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_totalDeaths() {
        var globalCaseData = GlobalDataProvider()
        val totalDeaths: ArrayList<String>? = globalCaseData.getGlobalCovidData("total_deaths")
        Thread.sleep(2000)
        assertNotEquals(totalDeaths!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_totalRecovered() {
        var globalCaseData = GlobalDataProvider()
        val totalRecovered: ArrayList<String>? = globalCaseData.getGlobalCovidData("total_recovered")
        Thread.sleep(2000)
        assertNotEquals(totalRecovered!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_newCases() {
        var globalCaseData = GlobalDataProvider()
        val newCases: ArrayList<String>? = globalCaseData.getGlobalCovidData("new_cases")
        Thread.sleep(2000)
        assertNotEquals(newCases!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_newDeaths() {
        var globalCaseData = GlobalDataProvider()
        val newDeaths: ArrayList<String>? = globalCaseData.getGlobalCovidData("new_deaths")
        Thread.sleep(2000)
        assertNotEquals(newDeaths!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_criticalCases() {
        var globalCaseData = GlobalDataProvider()
        val criticalCases: ArrayList<String>? = globalCaseData.getGlobalCovidData("serious_critical")
        Thread.sleep(2000)
        assertNotEquals(criticalCases!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_totalCasesPerOneMillionPopulation() {
        var globalCaseData = GlobalDataProvider()
        val totalCasesPerOneMillionPopulation: ArrayList<String>? = globalCaseData.getGlobalCovidData("total_cases_per_1m_population")
        Thread.sleep(2000)
        assertNotEquals(totalCasesPerOneMillionPopulation!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_deathsPerOneMillionPopulation() {
        var globalCaseData = GlobalDataProvider()
        val deathsPerOneMillionPopulation: ArrayList<String>? = globalCaseData.getGlobalCovidData("deaths_per_1m_population")
        Thread.sleep(2000)
        assertNotEquals(deathsPerOneMillionPopulation!!.size, 0)
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_statisticsTakenAt() {
        var globalCaseData = GlobalDataProvider()
        val statisticsTakenAt: ArrayList<String>? = globalCaseData.getGlobalCovidData("statistic_taken_at")
        Thread.sleep(2000)
        assertNotEquals(statisticsTakenAt!!.size, 0)
    }

}