package edu.uc.groupproject.covid19tracker.service

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class GlobalDataProvider {

    /**
     * Build http API request to retrieve global stats
     */
    private var client = OkHttpClient()
    private var request = Request.Builder()
        .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php")
        .get()
        .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
        .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36")
        .build()

    /**
     * Retrieve global number of covid19 cases
     */
    fun getGlobalCovidData(dataType: String): ArrayList<String>? {
        val data = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("onFailure", "onFailure called from getGlobalCovidData: ${e.printStackTrace()}")
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    /**
                     * On success API call, receive json body and store it into variable, then
                     * create a json object of the response body string so that function can
                     * retrieve specific data from json
                     */
                    val worldStat = response.body()!!.string()
                    val jsonObject = JSONObject(worldStat)
                    val o = jsonObject[dataType]
                    val s = o as String
                    data.add(s)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        return data
    }
}