package edu.uc.groupproject.covid19tracker.service

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class GlobalDataProvider {

    var client = OkHttpClient()
    var request = Request.Builder()
        .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php")
        .get()
        .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
        .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36")
        .build()

    fun getTotalCases(): ArrayList<String>? {
        val globalCases = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["total_cases"]
                    val s = o as String
                    globalCases.add(s)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        return globalCases
    }

    fun getTotalActiveCases(): ArrayList<String>? {
        val activeCases = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["active_cases"]
                    val s = o as String
                    activeCases.add(s)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
        return activeCases
    }

    fun getTotalDeaths(): ArrayList<String>? {
        val totalDeaths = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["total_deaths"]
                    val s = o as String
                    totalDeaths.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return totalDeaths
    }

    fun getTotalRecovered(): ArrayList<String>? {
        val totalRecovered = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["total_recovered"]
                    val s = o as String
                    totalRecovered.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return totalRecovered
    }

    fun getTotalNewCases(): ArrayList<String>? {
        val newCases = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["new_cases"]
                    val s = o as String
                    newCases.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return newCases
    }

    fun getTotalNewDeaths(): ArrayList<String>? {
        val newDeaths = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["new_deaths"]
                    val s = o as String
                    newDeaths.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return newDeaths
    }

    fun getTotalSeriousCritical(): ArrayList<String>? {
        val seriousCritical = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["serious_critical"]
                    val s = o as String
                    seriousCritical.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return seriousCritical
    }

    fun getTotalCasesPerMillionPopulation(): ArrayList<String>? {
        val casesPerMillion = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["total_cases_per_1m_population"]
                    val s = o as String
                    casesPerMillion.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return casesPerMillion
    }

    fun getTotalDeathsPerMillionPopulation(): ArrayList<String>? {
        val deathsPerMillion = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["deaths_per_1m_population"]
                    val s = o as String
                    deathsPerMillion.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return deathsPerMillion
    }

    fun getStatisticTakenTime(): ArrayList<String>? {
        val statisticTime = ArrayList<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                try {
                    val worldStat = response.body()!!.string()
                    val Jobject = JSONObject(worldStat)
                    val o = Jobject["statistic_taken_at"]
                    val s = o as String
                    statisticTime.add(s)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        })
        return statisticTime
    }
}