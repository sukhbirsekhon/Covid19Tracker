package edu.uc.groupproject.covid19tracker.service

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class CasesByCountryDataProvider {

    /**
     * Build http API request to retrieve covid19 cases data by country
     */
    private var client = OkHttpClient()
    private var request = Request.Builder()
        .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php")
        .get()
        .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
        .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36")
        .build()

    /**
     * Retrieve country by cases covid19 data
     */
    fun getCasesByCountryData(dataType: String):  ArrayList<String> {
        val data = ArrayList<String>()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("onFailure", "onFailure from getCasesByCountryData called : ${e.printStackTrace()}")
            }
            override fun onResponse(call: Call, response: Response) {
                try {
                    /**
                     * Retrieve cases by country covid 19 data from the API call and convert it into
                     * string. Then create a json object for that string body so that function can
                     * iterate through each country stat block and return array list of country stat
                     * data.
                     */
                    val casesByCountry = response.body()!!.string()
                    val casesObject = JSONObject(casesByCountry)
                    val casesArray = casesObject.getJSONArray("countries_stat")
                    for (i in 0 until casesArray.length()) {
                        val `object` = casesArray.getJSONObject(i).getString(dataType)
                        data.add(`object`)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        return data
    }

}