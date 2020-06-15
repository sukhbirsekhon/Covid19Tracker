package edu.uc.groupproject.covid19tracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.uc.groupproject.covid19tracker.dto.Cases
import edu.uc.groupproject.covid19tracker.service.CasesService

class MainViewModel : ViewModel() {
    var cases: MutableLiveData<ArrayList<Cases>> = MutableLiveData<ArrayList<Cases>>()
    var casesService: CasesService = CasesService()

    init {
        fetchGlobalData()
    }

    fun fetchGlobalData() {
        // call cases service to fetch global covid19 data
        cases = casesService.fetchGlobalData()
    }
}
