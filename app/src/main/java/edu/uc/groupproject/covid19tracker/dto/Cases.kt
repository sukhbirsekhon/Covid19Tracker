package edu.uc.groupproject.covid19tracker.dto

data class Cases(var activeCase: String, var fatalCase: String, var recoverdCase: String, var newCase: String, var newDeath: String, var globalConfirmed: String, var globalDeath: String, var globalRecovered: String) {}