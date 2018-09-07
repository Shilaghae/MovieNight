package com.movienight.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author anna
 */

public class MovieResponse<T> {

    @SerializedName("results")
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
