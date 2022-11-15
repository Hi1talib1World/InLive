package com.denzo.in_live.Model.Series;

import com.google.gson.annotations.SerializedName;
import java.util.List;

import java.io.Serializable;

public class SeasonListModel implements Serializable {

    @SerializedName("season_no")
    private String seasonNo;

    @SerializedName("episodes")
    private List<EpisodesItem> episodes;

    public void setSeasonNo(String seasonNo){
        this.seasonNo = seasonNo;
    }

    public String getSeasonNo(){
        return seasonNo;
    }

    public void setEpisodes(List<EpisodesItem> episodes){
        this.episodes = episodes;
    }

    public List<EpisodesItem> getEpisodes(){
        return episodes;
    }

    @Override
    public String toString(){
        return seasonNo;
    }
}