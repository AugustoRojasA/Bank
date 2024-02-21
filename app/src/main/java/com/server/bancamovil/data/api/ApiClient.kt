package com.server.bancamovil.data.api

import android.util.Log
import com.server.bancamovil.BancaMovilApp.token
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private const val READ_TIMEOUT: Long = 60
    private const val CONNECT_TIMEOUT: Long = 60
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://3090a301-8bb9-48b5-894d-2086ffc92cca.mock.pstmn.io"

    internal fun getAPiService(): AccountApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(AccountApiService::class.java)
    }
}