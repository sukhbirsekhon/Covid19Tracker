package edu.uc.groupproject.covid19tracker.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationLiveData = LocationLiveData(application)

    fun getLocationLiveData() = locationLiveData
}