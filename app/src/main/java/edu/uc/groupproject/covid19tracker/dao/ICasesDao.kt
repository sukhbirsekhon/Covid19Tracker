package edu.uc.groupproject.covid19tracker.dao

import edu.uc.groupproject.covid19tracker.dto.GlobalData
import retrofit2.Call
import retrofit2.http.GET
import kotlin.collections.ArrayList

interface ICasesDao {

    @GET("worldstat.php")
    fun getGlobalData(): Call<ArrayList<GlobalData>>

}