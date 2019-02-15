package com.example.fikridzakwan.basketballrestapi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;

public class ClubResponse {

    @SerializedName("teams")
    List<ClubData> teams;

    public List<ClubData> getTeams() {
        return teams;
    }
}
