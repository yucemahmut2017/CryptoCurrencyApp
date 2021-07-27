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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener{

    private val BASE_URL="https://api.nomics.com/v1/"
    private var cryptoModels:ArrayList<CryptoModel>?=null

    private var recyclerViewAdapter:RecyclerViewAdapter?=null






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        //https://api.nomics.com/v1/prices?key=2187154b76945f2373394aa34f7dc98a
        //2187154b76945f2373394aa34f7dc98a
        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        reycyclerView.layoutManager=layoutManager



        loadData()


    }
    private fun loadData(){
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service=retrofit.create(CryptoAPI::class.java)
        val call =service.getData()
         call.enqueue(object:Callback<List<CryptoModel>>{
             override fun onResponse(
                 call: Call<List<CryptoModel>>,
                 response: Response<List<CryptoModel>>
             ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModels=ArrayList(it)

                        cryptoModels?.let {
                            recyclerViewAdapter= RecyclerViewAdapter(it,this@MainActivity)
                            reycyclerView.adapter=recyclerViewAdapter



                        }

                    }
                }
             }

             override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
           t.printStackTrace()
             }

         })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
       Toast.makeText(this,"clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }
}