package com.siiberad.sproutdigital.network;

import com.siiberad.sproutdigital.model.ContactModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GetDataService {

    @GET("/api/users")
    Call<ContactModel> getAll();

//    @GET("/api/users")
//    Call<ContactModel> getAll(@Query("per_page") int per_page);
}
