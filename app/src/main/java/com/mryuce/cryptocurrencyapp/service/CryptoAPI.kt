package com.mryuce.cryptocurrencyapp.service



import com.mryuce.cryptocurrencyapp.model.CryptoModel

import retrofit2.Response
import retrofit2.http.GET


interface CryptoAPI {

    @GET("prices?key=b47b5e10dda54ccfe9b70b3ea654aaf06d03820f")
    suspend fun getData(): Response<List<CryptoModel>>

}
