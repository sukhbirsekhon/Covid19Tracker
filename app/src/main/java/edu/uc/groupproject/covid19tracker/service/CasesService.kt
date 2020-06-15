package edu.uc.groupproject.covid19tracker.service

import androidx.lifecycle.MutableLiveData
import edu.uc.groupproject.covid19tracker.RetrofitClientInstance
import edu.uc.groupproject.covid19tracker.dao.ICasesDao
import edu.uc.groupproject.covid19tracker.dto.Cases
import retrofit2.Call
import retrofit2.Response

class CasesService {
    fun fetchGlobalData() : MutableLiveData<ArrayList<Cases>> {
        var _globalData = MutableLiveData<ArrayList<Cases>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(ICasesDao::class.java)
        val call = service?.getGlobalData()
        call?.enqueue(object: retrofit2.Callback<ArrayList<Cases>> {
            override fun onFailure(call: Call<ArrayList<Cases>>, t: Throwable) {
                val j = 1+1;
                val i = 1+1;
            }

            override fun onResponse(
                call: Call<ArrayList<Cases>>,
                response: Response<ArrayList<Cases>>
            ) {
                // returns response body of API if the call is successful
                _globalData.value = response.body()
            }
        })

        return _globalData
    }
}