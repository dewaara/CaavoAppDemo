package com.dewaara.caavo.Remote;


import com.dewaara.caavo.Model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IMenuRequest {

    @GET
    Call<List<Item>> getMenuList(@Url String url);
}
