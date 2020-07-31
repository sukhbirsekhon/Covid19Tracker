package edu.uc.groupproject.covid19tracker

import edu.uc.groupproject.covid19tracker.service.CasesByCountryDataProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Tests confirming data is being received from Cases by Country API
 */
class CasesByCountryDataProviderTests {
    @Test
    fun retrieveCasesByCountryApiDataTest_countryName() {
        val casesByCountryData = CasesByCountryDataProvider()
        val country: ArrayList<String>? = casesByCountryData.getCasesByCountryData("country_name")
        Thread.sleep(2000)
        assertNotEquals(country!!.size, 0)
        assertEquals(country.contains("USA"), true)
        assertEquals(country.contains("Mexico"), true)
        assertEquals(country.contains("jfkasjflkajs"), false)
        assertEquals(country.contains("Mexco"), false)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_cases() {
        val casesByCountryData = CasesByCountryDataProvider()
        val cases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("cases")
        Thread.sleep(2000)
        assertNotEquals(cases!!.size, 0)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_deaths() {
        val casesByCountryData = CasesByCountryDataProvider()
        val deaths: ArrayList<String>? = casesByCountryData.getCasesByCountryData("deaths")
        Thread.sleep(2000)
        assertNotEquals(deaths!!.size, 0)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_activeCases() {
        val casesByCountryData = CasesByCountryDataProvider()
        val activeCases: ArrayList<String>? =
            casesByCountryData.getCasesByCountryData("active_cases")
        Thread.sleep(2000)
        assertNotEquals(activeCases!!.size, 0)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_totalRecovered() {
        val casesByCountryData = CasesByCountryDataProvider()
        val totalRecovered: ArrayList<String>? =
            casesByCountryData.getCasesByCountryData("total_recovered")
        Thread.sleep(2000)
        assertNotEquals(totalRecovered!!.size, 0)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_newDeaths() {
        val casesByCountryData = CasesByCountryDataProvider()
        val newDeaths: ArrayList<String>? = casesByCountryData.getCasesByCountryData("new_deaths")
        Thread.sleep(2000)
        assertNotEquals(newDeaths!!.size, 0)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_newCases() {
        val casesByCountryData = CasesByCountryDataProvider()
        val newCases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("new_cases")
        Thread.sleep(2000)
        assertNotEquals(newCases!!.size, 0)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_criticalCases() {
        val casesByCountryData = CasesByCountryDataProvider()
        val criticalCases: ArrayList<String>? =
            casesByCountryData.getCasesByCountryData("serious_critical")
        Thread.sleep(2000)
        assertNotEquals(criticalCases!!.size, 0)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_totalCasesPerMillionPopulation() {
        val casesByCountryData = CasesByCountryDataProvider()
        val totalCasesPerMillionPopulation: ArrayList<String>? =
            casesByCountryData.getCasesByCountryData("new_cases")
        Thread.sleep(2000)
        assertNotEquals(totalCasesPerMillionPopulation!!.size, 0)
    }
}
