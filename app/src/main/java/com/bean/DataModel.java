
package com.bean;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class DataModel {

    @SerializedName("resultCount")
    private Long mResultCount;
    @SerializedName("results")
    private List<Result> mResults;

    public Long getResultCount() {
        return mResultCount;
    }

    public void setResultCount(Long resultCount) {
        mResultCount = resultCount;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

}
