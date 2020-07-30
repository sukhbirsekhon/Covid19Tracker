package edu.uc.groupproject.covid19tracker

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.uc.groupproject.covid19tracker.service.CasesByCountryDataProvider
import edu.uc.groupproject.covid19tracker.service.GlobalDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("edu.uc.groupproject.covid19tracker", appContext.packageName)
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_countryName() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val country: ArrayList<String>? = casesByCountryData.getCasesByCountryData("country_name")
            delay(2000)
            assertNotEquals(country!!.size, 0)
            assertEquals(country!!.contains("USA"), true)
            assertEquals(country!!.contains("Mexico"), true)
            assertEquals(country!!.contains("jfkasjflkajs"), false)
            assertEquals(country!!.contains("Mexco"), false)
        }
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_cases() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val cases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("cases")
            delay(2000)
            assertNotEquals(cases!!.size, 0)
            assertNotEquals(cases!!.contains("cases"), true)
            assertNotEquals(cases!!.contains("1.5"), true)
        }
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_deaths() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val deaths: ArrayList<String>? = casesByCountryData.getCasesByCountryData("deaths")
            delay(2000)
            assertNotEquals(deaths!!.size, 0)
        }
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_activeCases() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val activeCases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("active_cases")
            delay(2000)
            assertNotEquals(activeCases!!.size, 0)
        }
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_totalRecovered() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val totalRecovered: ArrayList<String>? = casesByCountryData.getCasesByCountryData("total_recovered")
            delay(2000)
            assertNotEquals(totalRecovered!!.size, 0)
        }
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_newDeaths() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val newDeaths: ArrayList<String>? = casesByCountryData.getCasesByCountryData("new_deaths")
            delay(2000)
            assertEquals(newDeaths!!.size, 0)
        }
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_newCases() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val newCases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("new_cases")
            delay(2000)
            assertNotEquals(newCases!!.size, 0)
        }
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_criticalCases() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val criticalCases: ArrayList<String>? = casesByCountryData.getCasesByCountryData("serious_critical")
            delay(2000)
            assertNotEquals(criticalCases!!.size, 0)
        }
    }

    @Test
    fun retrieveCasesByCountryApiDataTest_totalCasesPerMillionPopulation() {
        var casesByCountryData = CasesByCountryDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val totalCasesPerMillionPopulation: ArrayList<String>? = casesByCountryData.getCasesByCountryData("new_cases")
            delay(2000)
            assertNotEquals(totalCasesPerMillionPopulation!!.size, 0)
        }
    }

    @Test
    fun retrieveCasesByGlobalApiDataTest_totalCases() {
        var globalCaseData = GlobalDataProvider()
        GlobalScope.launch(context = Dispatchers.Main) {
            val totalCases: ArrayList<String>? = globalCaseData.getGlobalCovidData("total_cases")
            delay(2000)
            assertNotEquals(totalCases!!.size, 0)
        }
    }
}
