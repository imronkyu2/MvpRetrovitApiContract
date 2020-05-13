package com.example.trainingretrofit.repository;

import androidx.annotation.Nullable;

import com.example.trainingretrofit.model.User;

import id.co.nexsoft.mvp.repo.BaseRepo;
import id.co.nexsoft.mvp.repo.GetCallback;
import id.co.nexsoft.mvp.repo.LoadCallback;
import id.co.nexsoft.mvp.repo.PostCallback;

public interface UserRepo extends BaseRepo<User> {
    void onCreate(User user, @Nullable PostCallback callback);

    void onUpdate(User user, @Nullable PostCallback callback);

    void getAllUserList(LoadCallback<User> callback);

    void onDelete(User user, @Nullable PostCallback callback);



    void doRemove(User user, @Nullable PostCallback callback);
    void getUserId(GetCallback callback);

}
