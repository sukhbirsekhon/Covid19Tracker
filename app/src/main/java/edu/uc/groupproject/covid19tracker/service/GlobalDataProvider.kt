package edu.uc.groupproject.covid19tracker.service

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class GlobalDataProvider {

    /**
     * Build http API request to retrieve global stats
     */
    var client = OkHttpClient()
    var request = Request.Builder()
        .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php")
        .get()
        .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
        .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36")
        .build()

    /**
     * Retrieve global number of covid19 cases
     */
    fun getTotalCases(): ArrayList<String>? {
        var globalCases = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["total_cases"]
                    var s = o as String
                    globalCases.add(s)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        return globalCases
    }

    /**
     * Retrieve global covid19 active cases
     */
    fun getTotalActiveCases(): ArrayList<String>? {
        var activeCases = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["active_cases"]
                    var s = o as String
                    activeCases.add(s)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        return activeCases
    }

    /**
     * Retrieve global number of covid19 deaths
     */
    fun getTotalDeaths(): ArrayList<String>? {
        var totalDeaths = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["total_deaths"]
                    var s = o as String
                    totalDeaths.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return totalDeaths
    }

    /**
     * Retrieve global recovered covid19 cases
     */
    fun getTotalRecovered(): ArrayList<String>? {
        var totalRecovered = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["total_recovered"]
                    var s = o as String
                    totalRecovered.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return totalRecovered
    }

    /**
     * Retrieve global new covid19 cases that occur every day
     */
    fun getTotalNewCases(): ArrayList<String>? {
        var newCases = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["new_cases"]
                    var s = o as String
                    newCases.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return newCases
    }

    /**
     * Retrieve global new covid19 deaths that occur every day
     */
    fun getTotalNewDeaths(): ArrayList<String>? {
        var newDeaths = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["new_deaths"]
                    var s = o as String
                    newDeaths.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return newDeaths
    }

    /**
     * Retrieve total number of global serious critical covid19 cases
     */
    fun getTotalSeriousCritical(): ArrayList<String>? {
        var seriousCritical = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["serious_critical"]
                    var s = o as String
                    seriousCritical.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return seriousCritical
    }

    /**
     * Retrieve global number of cases per million population
     */
    fun getTotalCasesPerMillionPopulation(): ArrayList<String>? {
        var casesPerMillion = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["total_cases_per_1m_population"]
                    var s = o as String
                    casesPerMillion.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return casesPerMillion
    }

    /**
     * Retrieve total deaths per million population
     */
    fun getTotalDeathsPerMillionPopulation(): ArrayList<String>? {
        var deathsPerMillion = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["deaths_per_1m_population"]
                    var s = o as String
                    deathsPerMillion.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return deathsPerMillion
    }

    /**
     * Retrieve the time when the data was updated
     */
    fun getStatisticTakenTime(): ArrayList<String>? {
        var statisticTime = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    var worldStat = response.body()!!.string()
                    var Jobject = JSONObject(worldStat)
                    var o = Jobject["statistic_taken_at"]
                    var s = o as String
                    statisticTime.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return statisticTime
    }
}