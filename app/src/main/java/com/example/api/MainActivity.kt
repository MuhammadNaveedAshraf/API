package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.profile_v01.Model.PersonData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsandblog.000webhostapp.com/wp-content/uploads/2022/11/"
class MainActivity : AppCompatActivity() {
    var receivedData = PersonData()
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receivedData = getMyData()
//        previous.setOnClickListener{
//            if(index>0){
//                index -= 1
//                tv_1.text = receivedData.fname.toString()
//                tv_2.text = receivedData.lname.toString()
//                tv_3.text = receivedData.fatherName.toString()
//                tv_4.text = receivedData.phone.toString()
//                page_no.text = (index+1).toString()
//            }
//        }
//        next.setOnClickListener{
//            if(index<receivedData.size-1){
//                index += 1
//                tv_1.text = receivedData[index].userId.toString()
//                tv_2.text = receivedData[index].id.toString()
//                tv_3.text = receivedData[index].title
//                tv_4.text = receivedData[index].body
//                page_no.text = (index+1).toString()
//
//            }
//        }
    }

    private fun getMyData() :PersonData{
        var receivedData = PersonData()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<PersonData?> {
            override fun onResponse(call: Call<PersonData?>, response: Response<PersonData?>) {
                val responseBody = response.body()
                receivedData = responseBody!!
//                println(responseBody)
//                for (myData in responseBody){
//                    val dataItem = DataItem(myData.userId,myData.id,myData.title,myData.body)
//                    receivedData.add(dataItem)
//                }
//                tv_1.text = receivedData[index].userId.toString()
//                tv_2.text = receivedData[index].id.toString()
//                tv_3.text = receivedData[index].title
//                tv_4.text = receivedData[index].body

                tv_1.text = receivedData.fname.toString()
                tv_2.text = receivedData.lname.toString()
                tv_3.text = receivedData.fatherName.toString()
                tv_4.text = receivedData.phone.toString()

            }

            override fun onFailure(call: Call<PersonData?>, t: Throwable) {
                Log.d("MainActivity","onFailure: "+t.message)
            }
        })
        return receivedData
    }
}