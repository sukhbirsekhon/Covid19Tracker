package edu.uc.groupproject.covid19tracker.dto

import com.google.gson.annotations.SerializedName

data class Cases(
    @SerializedName("country_name") var countryName: ArrayList<String>,
    @SerializedName("cases") var cases: ArrayList<String>,
    @SerializedName("deaths") var deaths: ArrayList<String>,
    @SerializedName("active_cases") var activeCases: ArrayList<String>,
    @SerializedName("total_recovered") var totalRecovered: ArrayList<String>,
    @SerializedName("new_deaths") var newDeaths: ArrayList<String>,
    @SerializedName("new_cases") var newCases: ArrayList<String>,
    @SerializedName("serious_critical") var seriousCritical: ArrayList<String>,
    @SerializedName("total_cases_per_1m_population") var totalCasesPerMillionPopulation: ArrayList<String>
)