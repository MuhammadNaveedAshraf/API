package com.example.api

import com.example.profile_v01.Model.PersonData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("person.json")
    fun getData():Call<PersonData>
}