package com.example.fikridzakwan.basketballrestapi.Model;

import com.google.gson.annotations.SerializedName;

public class ClubData {

    @SerializedName("idTeam")
    private String idTeam;

    @SerializedName("strTeam")
    private String strTeam;

    @SerializedName("strStadiumThumb")
    private String strStadiumThumb;

    @SerializedName("strStadiumDescription")
    private String strStadiumDescription;

    @SerializedName("strStadiumLocation")
    private String strStadiumLocation;

    @SerializedName("strDescriptionEN")
    private String strDescriptionEN;

    @SerializedName("strTeamBadge")
    private String strTeamBadge;

    public String getIdTeam() {
        return idTeam;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public String getStrStadiumThumb() {
        return strStadiumThumb;
    }

    public String getStrStadiumDescription() {
        return strStadiumDescription;
    }

    public String getStrStadiumLocation() {
        return strStadiumLocation;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }
}
