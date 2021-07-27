package com.mryuce.cryptocurrencyapp.service



import com.mryuce.cryptocurrencyapp.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET


interface CryptoAPI {

    @GET("prices?key=yourNomicsKey")
    fun getData():Observable<List<CryptoModel>>

}
