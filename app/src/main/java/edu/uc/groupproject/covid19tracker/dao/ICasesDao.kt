package edu.uc.groupproject.covid19tracker.dao

import edu.uc.groupproject.covid19tracker.dto.Cases
import retrofit2.Call
import retrofit2.http.GET

interface ICasesDao {

    @GET("/world/total")
    fun getGlobalData(): Call<ArrayList<Cases>>

}