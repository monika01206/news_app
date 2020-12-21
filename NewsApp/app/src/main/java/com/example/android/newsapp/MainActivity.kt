package com.example.android.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.newsapp.adapter.NewsAdapter
import com.example.android.newsapp.api.New
import com.example.android.newsapp.api.NewsApiJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "https://api.currentsapi.services"
class MainActivity : AppCompatActivity() {
    lateinit var countDownTimer: CountDownTimer
    private var titleList = mutableListOf<String>()
    private var detailsList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var linksList = mutableListOf<String>()
    private var authorList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeAPIRequest()

    }

    private fun setUpRecyclerView(){
        var recyclerView:RecyclerView= findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = NewsAdapter(titleList,detailsList,imagesList,linksList,authorList)
    }

    private fun addToList(title : String ,details :String , images : String, link :String,author:String){
            titleList.add(title)
            detailsList.add(details)
            imagesList.add(images)
            linksList.add(link)
            authorList.add(author)
    }
    private fun  makeAPIRequest(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            try{
                val response = api.getNews()
                for(article in response.news){
                    //Log.i("MainActivity","Result=$article")
                    addToList(article.title,article.description,article.image,article.url,article.author)
                }
                withContext(Dispatchers.Main){
                    setUpRecyclerView()
                }
            }catch (e:Exception){
                Log.e("MainActivity" ,e.toString())
            }
        }
    }

}