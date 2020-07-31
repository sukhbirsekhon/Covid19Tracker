package edu.uc.groupproject.covid19tracker.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.uc.groupproject.covid19tracker.dto.Cases
import edu.uc.groupproject.covid19tracker.dto.FirebaseGlobalData
import edu.uc.groupproject.covid19tracker.dto.GlobalData
import edu.uc.groupproject.covid19tracker.dto.News
import edu.uc.groupproject.covid19tracker.service.CasesByCountryDataProvider
import edu.uc.groupproject.covid19tracker.service.GlobalDataProvider
import edu.uc.groupproject.covid19tracker.service.NewsDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {
    var gmData: MutableLiveData<GlobalData> = MutableLiveData<GlobalData>()
    var cData: MutableLiveData<Cases> = MutableLiveData<Cases>()
    var nData: MutableLiveData<News> = MutableLiveData<News>()

    var firestore = Firebase.firestore
    var firebaseGData: FirebaseGlobalData = FirebaseGlobalData()
    var fireData: MutableLiveData<FirebaseGlobalData> = MutableLiveData<FirebaseGlobalData>()

    var globalDataDto = GlobalData()

    lateinit var firebaseActiveCases: String
    lateinit var firebaseFatalCases: String
    lateinit var firebaseTotalCases: String
    lateinit var firebaseRecoveredCases: String

    init {
        globalData()
    }

    fun globalData() {
        GlobalScope.launch(context = Dispatchers.Main) {
            assignGlobalData()
            assignCountryByCasesData()
            assignNewsData()
        }
    }

    private suspend fun assignNewsData() {
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
        val newsDto =
            News(author!!, title!!, description!!, url!!, urlToImage!!, publishedAt!!, content!!)

        nData.value = newsDto

        uploadFirebaseNewsData(newsDto)
    }

    fun uploadFirebaseNewsData(newsDto: News) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val collection = firestore.collection("News").document(LocalDateTime.now().toString())
        collection.set(newsDto)
    }

    private suspend fun assignCountryByCasesData() {
        val casesByCountry = CasesByCountryDataProvider()

        /**
         * Call API to retrieve cases by country data
         */
        val country: ArrayList<String>? = casesByCountry.getCasesByCountryData("country_name")
        val cases: ArrayList<String>? = casesByCountry.getCasesByCountryData("cases")
        val deaths: ArrayList<String>? = casesByCountry.getCasesByCountryData("deaths")
        val active: ArrayList<String>? = casesByCountry.getCasesByCountryData("active_cases")
        val totalRecovered: ArrayList<String>? =
            casesByCountry.getCasesByCountryData("total_recovered")
        val newDeaths: ArrayList<String>? = casesByCountry.getCasesByCountryData("new_deaths")
        val newCases: ArrayList<String>? = casesByCountry.getCasesByCountryData("new_cases")
        val seriousCritical: ArrayList<String>? =
            casesByCountry.getCasesByCountryData("serious_critical")
        val totalCasesPerMillionPopulation: ArrayList<String>? =
            casesByCountry.getCasesByCountryData("total_cases_per_1m_population")
        delay(2000)

        /**
         * Set fetched country data to DTO object
         */
        val casesDto = Cases(
            country!!, cases!!, deaths!!, active!!, totalRecovered!!, newDeaths!!,
            newCases!!, seriousCritical!!, totalCasesPerMillionPopulation!!
        )

        cData.value = casesDto

        uploadFirebaseCasesByCountryData(casesDto)
    }

    fun uploadFirebaseCasesByCountryData(casesDto: Cases) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val collection =
            firestore.collection("Cases By Country").document(LocalDateTime.now().toString())
        collection.set(casesDto)
    }

    private suspend fun assignGlobalData() {
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
        val gCasesPerMillion: ArrayList<String>? =
            gdp.getGlobalCovidData("total_cases_per_1m_population")
        val gDeathPerMillion: ArrayList<String>? =
            gdp.getGlobalCovidData("deaths_per_1m_population")
        val gStatistics: ArrayList<String>? = gdp.getGlobalCovidData("statistic_taken_at")
        delay(2000)

        globalDataDto = GlobalData(
            gCases!![0], gActive!![0], gTotalDeath!![0], gRecovered!![0],
            gNewCases!![0], gNewDeaths!![0], gSeriousCritical!![0], gCasesPerMillion!![0],
            gDeathPerMillion!![0], gStatistics!![0]
        )

        firebaseGData = FirebaseGlobalData(gCases[0], gActive[0], gTotalDeath[0], gRecovered[0])

        fireData.value = firebaseGData

        uploadFirebaseGlobalData(firebaseGData)
        retrieveFirebaseGlobalData()
    }

    fun uploadFirebaseGlobalData(firebaseGData: FirebaseGlobalData) {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        val collection =
            firestore.collection("Global Data").document(LocalDateTime.now().toString())
        collection.set(firebaseGData)
    }

    fun retrieveFirebaseGlobalData() {
        firestore.collection("Global Data").get().addOnSuccessListener { result ->
            firebaseActiveCases = result.documents.last().data!!["active"].toString()
            firebaseRecoveredCases = result.documents.last().data!!["recovered"].toString()
            firebaseFatalCases = result.documents.last().data!!["totalDeath"].toString()
            firebaseTotalCases = result.documents.last().data!!["cases"].toString()

            Log.d("Active", firebaseActiveCases)
            Log.d("Fatal", firebaseFatalCases)
            Log.d("Total", firebaseTotalCases)
            Log.d("Recovered", firebaseRecoveredCases)

            firebaseGData = FirebaseGlobalData(
                firebaseTotalCases,
                firebaseActiveCases,
                firebaseTotalCases,
                firebaseRecoveredCases
            )
        }
    }
}
