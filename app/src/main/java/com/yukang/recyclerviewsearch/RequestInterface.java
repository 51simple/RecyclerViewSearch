package com.yukang.recyclerviewsearch;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by simple on 17-3-11.
 */

public interface RequestInterface {

    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();
}
