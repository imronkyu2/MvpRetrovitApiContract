package com.example.trainingretrofit.data_source_api;

import androidx.room.Delete;

import com.example.trainingretrofit.model.User;

import java.util.HashMap;
import java.util.Map;

import id.co.nexsoft.impl.model.BaseData;
import id.co.nexsoft.impl.model.response.BaseResult;
import id.co.nexsoft.mvp.repo.BasePaging;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;

public interface UserApiService {

    @POST("api/yogi/user/create")
    Call<BaseData<BaseResult<User>>> onCreate(@Body User user);

    @PUT("api/yogi/user")
    Call<BaseData<BaseResult<User>>> onUpdate(@Body HashMap user);
//    Call<BaseData<BaseResult<BasePaging<User>>>> onUpdate(User user);

    @GET("api/yogi/user/list")
    Call<BaseData<BaseResult<BasePaging<User>>>> getAll(@QueryMap Map<String, Object> map);

    @HTTP(method= "DELETE", path="api/yogi/user", hasBody = true)
    Call<BaseData<BaseResult<User>>> onDeleteUser(@Body User user);

}
