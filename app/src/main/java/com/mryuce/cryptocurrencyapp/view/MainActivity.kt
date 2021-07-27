package com.mryuce.cryptocurrencyapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mryuce.cryptocurrencyapp.R
import com.mryuce.cryptocurrencyapp.adapter.RecyclerViewAdapter
import com.mryuce.cryptocurrencyapp.model.CryptoModel
import com.mryuce.cryptocurrencyapp.service.CryptoAPI

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener{

    private val BASE_URL="https://api.nomics.com/v1/"
    private var cryptoModels:ArrayList<CryptoModel>?=null

    private var recyclerViewAdapter:RecyclerViewAdapter?=null

    private var job: Job?=null


    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Error: ${throwable.localizedMessage}")}







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        reycyclerView.layoutManager=layoutManager




        loadData()


    }
    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoAPI::class.java)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = retrofit.getData()

            withContext(Dispatchers.Main){
                if(response.isSuccessful) {
                    response.body()?.let {
                        handleResponse(it)
                    }
                }
            }
        }



    }

    private fun handleResponse(cryptoList: List<CryptoModel>){
        cryptoModels=ArrayList(cryptoList)
        cryptoModels?.let {
            recyclerViewAdapter= RecyclerViewAdapter(it,this)
            reycyclerView.adapter=recyclerViewAdapter
        }}
    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()

    }
}