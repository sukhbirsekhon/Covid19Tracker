package edu.uc.groupproject.covid19tracker.dto

import com.google.gson.annotations.SerializedName

data class GlobalData(@SerializedName("total_cases") var totalCases: String,
                      @SerializedName("active_cases") var activeCases: String,
                      @SerializedName("total_deaths") var totalDeaths: String,
                      @SerializedName("total_recovered") var totalRecovered: String,
                      @SerializedName("new_cases") var newCases: String,
                      @SerializedName("new_deaths") var newDeaths: String,
                      @SerializedName("serious_critical") var seriousCritical: String,
                      @SerializedName("total_cases_per_1m_population") var totalCasesPer1mPopulation: String,
                      @SerializedName("deaths_per_1m_population") var deathPer1mPopulation: String,
                      @SerializedName("statistic_taken_at") var statisticTakenAt: String) {
    override fun toString(): String {
        return "$totalCases $activeCases  $totalDeaths  $totalRecovered $newCases $newDeaths $seriousCritical $totalCasesPer1mPopulation $deathPer1mPopulation $statisticTakenAt"
    }
}