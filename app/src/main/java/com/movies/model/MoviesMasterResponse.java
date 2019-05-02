package com.movies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MoviesMasterResponse implements Serializable {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Results> results = null;

    private String requestType = "";
    private String label = "";


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        if (null == results) {
            return;
        }
        this.results = results;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String apiCallType) {
        this.requestType = apiCallType;
    }

    public String getLabel() {

        if (null != label) {
            label = label.replace("_", " ").toUpperCase();
        }
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
