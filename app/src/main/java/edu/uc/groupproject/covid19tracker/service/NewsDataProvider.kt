package edu.uc.groupproject.covid19tracker.service

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class NewsDataProvider {

    fun getNewsData(dataType: String): ArrayList<String>?  {
         val client = OkHttpClient()
         val request:Request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?q=coronavirus&sortBy=popularity&apiKey=6c8417610d7e44a59e12d3758d004968")
            .get()
            .build()

        val data = ArrayList<String>()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("onFailure", "onFailure called from getNewsData: ${e.printStackTrace()}")
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    /**
                     * On success API call, receive json body and store it into variable, then
                     * create a json object of the response body string so that function can
                     * retrieve specific data from json
                     */
                    val newsData: String = response.body()!!.string()
                    val newsObject = JSONObject(newsData)
                    val newsObjectArray = newsObject.getJSONArray("articles")

                    for (i in 0 until newsObjectArray.length()) {
                        val o = newsObjectArray.getJSONObject(i).getString(dataType)
                        data.add(o)
                    }
                } catch (e: Exception) {e.printStackTrace()}
            }
        })
        return data
    }

}