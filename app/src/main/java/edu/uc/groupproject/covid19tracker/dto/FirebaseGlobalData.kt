package edu.uc.groupproject.covid19tracker.dto

data class FirebaseGlobalData(
    var cases: String = "",
    var active: String = "",
    var totalDeath: String = "",
    var recovered: String = ""
)