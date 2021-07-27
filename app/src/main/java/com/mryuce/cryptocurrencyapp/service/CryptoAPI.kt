package com.mryuce.cryptocurrencyapp.service


import com.mryuce.cryptocurrencyapp.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET
import javax.security.auth.callback.Callback

interface CryptoAPI {

    @GET("prices?key=yourNomicsKey")
    fun getData():Call<List<CryptoModel>>

}
