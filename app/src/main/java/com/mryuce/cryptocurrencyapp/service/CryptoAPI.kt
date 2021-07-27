package com.mryuce.cryptocurrencyapp.service

import com.mryuce.cryptocurrencyapp.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //https://api.nomics.com/v1
    //prices?key=b47b5e10dda54ccfe9b70b3ea654aaf06d03820f"
    @GET("prices?key=b47b5e10dda54ccfe9b70b3ea654aaf06d03820f")
    fun getData():Call<List<CryptoModel>>

}