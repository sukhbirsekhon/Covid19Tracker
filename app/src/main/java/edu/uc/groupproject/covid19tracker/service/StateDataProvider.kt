package edu.uc.groupproject.covid19tracker.service

import android.util.Log
import com.google.gson.JsonObject
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList

class StateDataProvider {

    /**
     * Retrieve global number of covid19 cases
     */
    fun getStateData(stateCode: String, dataType: String): ArrayList<Int>? {
        /**
         * Build http API request to retrieve global stats
         */
         var client = OkHttpClient()
         var request = Request.Builder()
            .url("https://covidtracking.com/api/v1/states/" + stateCode + "/current.json")
            .get()
            .build()

        val data = ArrayList<Int>()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("onFailure", "onFailure called from getStateData: ${e.printStackTrace()}")
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    /**
                     * On success API call, receive json body and store it into variable, then
                     * create a json object of the response body string so that function can
                     * retrieve specific data from json
                     */
                    val stateData = response.body()!!.string()
                    val stateObject = JSONObject(stateData)
                    val dataObject = stateObject[dataType]
                    if(dataObject is Int) {
                        val element = dataObject
                        data.add(element)
                    } else if(dataObject is JsonObject){
                        val integer = 0
                        data.add(integer)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        return data
    }
}