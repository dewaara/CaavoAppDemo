package com.dewaara.caavo.Remote

import com.dewaara.caavo.Model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IMenuRequest {
    @GET
    fun getMenuList(@Url url: String?): Call<List<Item?>?>
}