package com.example.trainingretrofit.activity_create_user.create_presenter;

import androidx.annotation.NonNull;

import com.example.trainingretrofit.activity_create_user.create_contract.ICreatePresenterContract;
import com.example.trainingretrofit.activity_create_user.create_contract.ICreateViewContract;
import com.example.trainingretrofit.model.User;
import com.example.trainingretrofit.repository.UserRepo;

import id.co.nexsoft.mvp.repo.PostCallback;

public class CreateUserPresenter implements ICreatePresenterContract {
    private ICreateViewContract iCreateViewContract;
    private UserRepo creteUserRepo;

    public CreateUserPresenter(UserRepo creteUserRepo, Object o, ICreateViewContract iCreateViewContract){
        this.iCreateViewContract = iCreateViewContract;
        this.creteUserRepo = creteUserRepo;
        iCreateViewContract.setPresenter(this);
    }

    @Override
    public void onCreate(@NonNull User dataUser) {

        iCreateViewContract.showContentLoading();

        creteUserRepo.onCreate(dataUser, new PostCallback() {
            @Override
            public void onEntityPosted(Object entity) {
                iCreateViewContract.onCreteSucces((User) entity);
            }

            @Override
            public void onErrorRequest(Throwable throwable) {
                iCreateViewContract.hideContentLoading();

            }
        });
    }

    @Override
    public void start(Boolean aBoolean) {

    }

}
