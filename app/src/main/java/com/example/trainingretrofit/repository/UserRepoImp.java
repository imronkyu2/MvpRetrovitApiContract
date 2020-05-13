package com.example.trainingretrofit.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trainingretrofit.data_source_api.RetrofitHttpCall;
import com.example.trainingretrofit.data_source_api.UserApiService;
import com.example.trainingretrofit.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import id.co.nexsoft.impl.model.BaseData;
import id.co.nexsoft.impl.model.response.BaseResult;
import id.co.nexsoft.mvp.base.BaseResponseCallback;
import id.co.nexsoft.mvp.repo.BasePaging;
import id.co.nexsoft.mvp.repo.GetCallback;
import id.co.nexsoft.mvp.repo.LoadCallback;
import id.co.nexsoft.mvp.repo.PostCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class UserRepoImp implements UserRepo {
    private static UserRepoImp INSTANCE = null;
    private Context context;

    public static UserRepo getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = (new UserRepoImp());
            INSTANCE.context = context;
        }
        return INSTANCE;
    }


    @Override
    public void onCreate(User user, @Nullable final PostCallback callback) {
        Log.i(TAG, "onCreate Firt Name: "+ user.getFirstName());
        Log.i(TAG, "onCreate Last Name: "+ user.getLastName());
        Log.i(TAG, "onCreate Birt Date: "+ user.getBirthDate());

        user.setUsername("SYSTEM");
        user.setFrom("SYSTEM");

        HashMap hashMap = new HashMap();
        hashMap.put("firstName", user.getFirstName());
        hashMap.put("lastName", user.getLastName());
        hashMap.put("birthDate", user.getBirthDate());
        hashMap.put("password", user.getPassword());
        hashMap.put("confirmPassword", user.getConfirmPassword());
        hashMap.put("phone", user.getPhone());
        hashMap.put("userName", user.getUsername());
        hashMap.put("from", user.getFrom());

        UserApiService service = RetrofitHttpCall.getInstance().create(UserApiService.class);
        Call<BaseData<BaseResult<User>>> request = service.onCreate(user);
        request.enqueue(new Callback<BaseData<BaseResult<User>>>() {
            @Override
            public void onResponse(Call<BaseData<BaseResult<User>>>call, Response<BaseData<BaseResult<User>>> response) {
                assert callback != null;
               if (response.isSuccessful()) {
                   callback.onEntityPosted(response.body().getAdditionalEntity().getResult());
               }else {
                   try {
                       ObjectMapper objectMapper = new ObjectMapper();
                       BaseData<HashMap> groBaseData = objectMapper.readValue(response.errorBody().byteStream(), BaseData.class);
                       if (groBaseData.getCode().equals("400-VALIDATION")) {
                           callback.onErrorRequest(new Throwable(groBaseData.description + " , " + groBaseData.getAdditionalEntity().get("errors")));
                       } else {
                           callback.onErrorRequest(new Throwable(groBaseData.description));
                       }

                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
            }

            @Override
            public void onFailure(Call<BaseData<BaseResult<User>>> call, Throwable t) {
                assert callback != null;
                callback.onErrorRequest(t);
            }
        });


    }

    @Override
    public void onUpdate(User user, @Nullable final PostCallback callback) {
        Log.i(TAG, "onCreate: "+ user.getFirstName());
        Log.i(TAG, "onCreate: "+ user.getLastName());
        Log.i(TAG, "onCreate: "+ user.getBirthDate());
        Log.i(TAG, "onCreate: "+ user.getPassword());

        user.setUsername("SYSTEM");
        user.setFrom("SYSTEM");

        HashMap hashMap = new HashMap();
        hashMap.put("userID", user.getUserId());
        hashMap.put("firstName", user.getFirstName());
        hashMap.put("lastName", user.getLastName());
        hashMap.put("birthDate", user.getBirthDate());
        hashMap.put("password", user.getPassword());
        hashMap.put("confirmPassword", user.getConfirmPassword());
        hashMap.put("phone", user.getPhone());
        hashMap.put("userName", user.getUsername());
        hashMap.put("from", user.getFrom());


        UserApiService service = RetrofitHttpCall.getInstance().create(UserApiService.class);
        Call<BaseData<BaseResult<User>>> request = service.onUpdate(hashMap);
        request.enqueue(new Callback<BaseData<BaseResult<User>>>(){
            @Override
            public void onResponse(Call<BaseData<BaseResult<User>>> call, Response<BaseData<BaseResult<User>>> response) {
                assert callback != null;
                if (response.isSuccessful()) {
                    callback.onEntityPosted(response.body().getAdditionalEntity().getResult());
                }else {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        BaseData<HashMap> groBaseData = objectMapper.readValue(response.errorBody().byteStream(), BaseData.class);
                        if (groBaseData.getCode().equals("400-VALIDATION")) {
                            callback.onErrorRequest(new Throwable(groBaseData.description + " , " + groBaseData.getAdditionalEntity().get("errors")));
                        } else {
                            callback.onErrorRequest(new Throwable(groBaseData.description));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseData<BaseResult<User>>> call, Throwable t) {
                assert callback != null;
                callback.onErrorRequest(t);
            }
        });

    }

    @Override
    public void getAllUserList(final LoadCallback<User> callback) {
        HashMap param = new HashMap();
        param.put("showAll", true);
        param.put("orderBy", "none");
        param.put("filter", "none");
        param.put("pageIndex", 1);
        param.put("pageSize",50);
        UserApiService service = RetrofitHttpCall.getInstance().create(UserApiService.class);
        Call<BaseData<BaseResult<BasePaging<User>>>> request = service.getAll(param);
        request.enqueue(new BaseResponseCallback<BaseData<BaseResult<BasePaging<User>>>>(context, callback) {
            @Override
            public void onCustomResponse(Call<BaseData<BaseResult<BasePaging<User>>>> call, Response<BaseData<BaseResult<BasePaging<User>>>> response) {
                assert callback != null;
                if (response.isSuccessful()) {
                    callback.onLoadedData(response.body().getAdditionalEntity().getResult().getResult());
                }else {
                    callback.onErrorRequest(new Throwable());
                }
            }
        });

    }

    @Override
    public void onDelete(User user, @Nullable final PostCallback callback) {
        user.setUsername("SYSTEM");
        user.setFrom("SYSTEM");

        HashMap hashMap = new HashMap();
        hashMap.put("userID", user.getUserId());
        hashMap.put("userName", user.getUsername());
        hashMap.put("from", user.getFrom());

        UserApiService service = RetrofitHttpCall.getInstance().create(UserApiService.class);
        Call<BaseData<BaseResult<User>>> request = service.onDeleteUser(user);
        request.enqueue(new Callback<BaseData<BaseResult<User>>>() {
            @Override
            public void onResponse(Call<BaseData<BaseResult<User>>> call, Response<BaseData<BaseResult<User>>> response) {
                assert callback != null;
                if (response.isSuccessful()) {
                    callback.onEntityPosted(response.body().getAdditionalEntity().getResult());
                }else {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        BaseData<HashMap> groBaseData = objectMapper.readValue(response.errorBody().byteStream(), BaseData.class);
                        if (groBaseData.getCode().equals("400-VALIDATION")) {
                            callback.onErrorRequest(new Throwable(groBaseData.description + " , " + groBaseData.getAdditionalEntity().get("errors")));
                        } else {
                            callback.onErrorRequest(new Throwable(groBaseData.description));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<BaseData<BaseResult<User>>> call, Throwable t) {
                assert callback != null;
                callback.onErrorRequest(t);
            }
        });


    }

    @Override
    public void doRemove(User user, @Nullable PostCallback callback) {

    }

    @Override
    public void getUserId(GetCallback callback) {

    }

    @Override
    public void getListEntity(@NonNull HashMap hashMap, @NonNull LoadCallback<User> loadCallback) {

    }

    @Override
    public void getEntity(@NonNull Serializable serializable, @NonNull GetCallback<User> getCallback) {

    }

    @Override
    public void save(@NonNull User user, @Nullable PostCallback postCallback) {

    }

    @Override
    public void refreshList() {

    }

    @Override
    public void deleteAllEntity() {

    }

    @Override
    public void delete(@NonNull Serializable serializable, @Nullable PostCallback postCallback) {

    }

    @Override
    public void setConnectInternet(boolean b) {

    }
}