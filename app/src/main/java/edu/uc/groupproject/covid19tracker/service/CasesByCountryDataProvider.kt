package edu.uc.groupproject.covid19tracker.service

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class CasesByCountryDataProvider {

    /**
     * Build http API request to retrieve covid19 cases data by country
     */
    var client = OkHttpClient()
    var request = Request.Builder()
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
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    /**
                     * Retrieve cases by country covid 19 data from the API call and convert it into
                     * string. Then create a json object for that string body so that function can
                     * iterate through each country stat block and return array list of country stat
                     * data.
                     */
                    val casesByCountry = response.body()!!.string()
                    val jObject = JSONObject(casesByCountry)
                    val jArray = jObject.getJSONArray("countries_stat")
                    for (i in 0 until jArray.length()) {
                        val objString = jArray.getJSONObject(i).getString(dataType)
                        data.add(objString)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        return data
    }
}