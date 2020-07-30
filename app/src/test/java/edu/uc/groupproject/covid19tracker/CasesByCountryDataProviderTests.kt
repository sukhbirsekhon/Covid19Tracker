package edu.uc.groupproject.covid19tracker

import edu.uc.groupproject.covid19tracker.service.CasesByCountryDataProvider
import edu.uc.groupproject.covid19tracker.service.GlobalDataProvider

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CasesByCountryDataProviderTests {
    @Test
    fun retrieveCasesByCountryApiDataTest_countryName() {
        var casesByCountryData = CasesByCountryDataProvider()
        var country: ArrayList<String>? = casesByCountryData.getCasesByCountryData("country_name")
        Thread.sleep(2000)
        assertNotEquals(country!!.size, 0)
        assertEquals(country!!.contains("USA"), true)
        assertEquals(country!!.contains("Mexico"), true)
        assertEquals(country!!.contains("jfkasjflkajs"), false)
        assertEquals(country!!.contains("Mexco"), false)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_cases() {
        var casesByCountryData = CasesByCountryDataProvider()
        val cases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("cases")
        Thread.sleep(2000)
        assertNotEquals(cases!!.size, 0)
        assertNotEquals(cases!!.contains("cases"), true)
        assertNotEquals(cases!!.contains("1.5"), true)

    }

    @Test
    fun retrieveCasesByCountryApiDataTest_deaths() {
        var casesByCountryData = CasesByCountryDataProvider()
        val deaths: ArrayList<String>? = casesByCountryData.getCasesByCountryData("deaths")
        Thread.sleep(2000)
        assertNotEquals(deaths!!.size, 0)

    }

    @Test
    fun retrieveCasesByCountryApiDataTest_activeCases() {
        var casesByCountryData = CasesByCountryDataProvider()
        val activeCases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("active_cases")
        Thread.sleep(2000)
        assertNotEquals(activeCases!!.size, 0)

    }

    @Test
    fun retrieveCasesByCountryApiDataTest_totalRecovered() {
        var casesByCountryData = CasesByCountryDataProvider()
        val totalRecovered: ArrayList<String>? = casesByCountryData.getCasesByCountryData("total_recovered")
        Thread.sleep(2000)
        assertNotEquals(totalRecovered!!.size, 0)

    }

    @Test
    fun retrieveCasesByCountryApiDataTest_newDeaths() {
        var casesByCountryData = CasesByCountryDataProvider()
        val newDeaths: ArrayList<String>? = casesByCountryData.getCasesByCountryData("new_deaths")
        Thread.sleep(2000)
        assertNotEquals(newDeaths!!.size, 0)

    }

    @Test
    fun retrieveCasesByCountryApiDataTest_newCases() {
        var casesByCountryData = CasesByCountryDataProvider()
        val newCases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("new_cases")
        Thread.sleep(2000)
        assertNotEquals(newCases!!.size, 0)

    }

    @Test
    fun retrieveCasesByCountryApiDataTest_criticalCases() {
        var casesByCountryData = CasesByCountryDataProvider()
        val criticalCases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("serious_critical")
        Thread.sleep(2000)
        assertNotEquals(criticalCases!!.size, 0)

    }

    @Test
    fun retrieveCasesByCountryApiDataTest_totalCasesPerMillionPopulation() {
        var casesByCountryData = CasesByCountryDataProvider()
        val totalCasesPerMillionPopulation: ArrayList<String>? = casesByCountryData.getCasesByCountryData("new_cases")
        Thread.sleep(2000)
        assertNotEquals(totalCasesPerMillionPopulation!!.size, 0)

    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_totalCases() {
        var globalCaseData = GlobalDataProvider()
        val totalCases: ArrayList<String>? = globalCaseData.getGlobalCovidData("total_cases")
        Thread.sleep(2000)
        assertNotEquals(totalCases!!.size, 0)

    }
}
