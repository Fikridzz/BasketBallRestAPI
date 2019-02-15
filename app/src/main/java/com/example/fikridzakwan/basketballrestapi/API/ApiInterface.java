package com.example.fikridzakwan.basketballrestapi.API;

import com.example.fikridzakwan.basketballrestapi.Model.ClubResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/v1/json/1/lookup_all_teams.php")
    Call<ClubResponse> getClub (@Query("id") int idClub);

    @GET("/api/v1/json/1/lookupteam.php")
    Call<ClubResponse> getDetail (@Query("id") int idDetail);
}
