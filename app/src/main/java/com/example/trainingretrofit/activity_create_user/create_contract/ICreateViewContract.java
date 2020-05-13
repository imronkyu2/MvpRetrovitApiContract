package com.example.trainingretrofit.activity_create_user.create_contract;

import com.example.trainingretrofit.model.User;

import id.co.nexsoft.mvp.base.BaseView;

public interface ICreateViewContract extends BaseView<ICreatePresenterContract> {

    void onCreteSucces(User user);
//    void onCreteError(String message);
}
