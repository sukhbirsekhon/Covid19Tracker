package edu.uc.groupproject.covid19tracker.service

import androidx.lifecycle.MutableLiveData
import edu.uc.groupproject.covid19tracker.RetrofitClientInstance
import edu.uc.groupproject.covid19tracker.dao.ICasesDao
import edu.uc.groupproject.covid19tracker.dto.GlobalData
import retrofit2.Call
import retrofit2.Response

class CasesService {
    fun fetchGlobalData() : MutableLiveData<ArrayList<GlobalData>> {
        var _globalData = MutableLiveData<ArrayList<GlobalData>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(ICasesDao::class.java)
        val call = service?.getGlobalData()
        call?.enqueue(object: retrofit2.Callback<ArrayList<GlobalData>> {
            override fun onFailure(call: Call<ArrayList<GlobalData>>, t: Throwable) {
                println("Error recieved on API call: " + t.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<GlobalData>>,
                response: Response<ArrayList<GlobalData>>
            ) {
                /**
                 * returns response body of API if the call is successful
                 */
                _globalData.value = response.body()
                println("Successfully called the API")
            }
        })

        return _globalData
    }
}