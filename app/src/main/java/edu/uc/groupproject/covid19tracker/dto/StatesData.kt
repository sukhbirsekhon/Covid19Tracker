package edu.uc.groupproject.covid19tracker.dto

data class StatesData(
    var date: Int = 0, var total: Int = 0, var positive: Int = 0,
    var death: Int = 0, var hospitalizedCurrently: Int = 0
) {
    override fun toString(): String {
        return ("date: $date total: $total positive: $positive death: $death hospitalizedCurrently: $hospitalizedCurrently")
    }
}