package edu.uc.groupproject.covid19tracker

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/"

    val retrofitInstance : Retrofit?
        get() {
            /**
             * If retrofit is null then create http request for API call and then create a retrofit
             * request as well
             */
            if (retrofit == null) {
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response? {
                        val request: Request =
                            chain.request().newBuilder().addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                                .addHeader("x-rapidapi-key", "12a8dba6admshd5f767ad7c36e5bp17cb05jsn3c9cee783e36").build()
                        return chain.proceed(request)
                    }
                })
                retrofit = retrofit2.Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }
}