package edu.uc.groupproject.covid19tracker.dao

import edu.uc.groupproject.covid19tracker.dto.GlobalData
import retrofit2.Call
import retrofit2.http.GET

interface ICasesDao {

    @GET("worldstat.php")
    fun getGlobalData(): Call<ArrayList<GlobalData>>

}