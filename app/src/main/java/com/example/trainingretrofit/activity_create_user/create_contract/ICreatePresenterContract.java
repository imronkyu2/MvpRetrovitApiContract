package com.example.trainingretrofit.activity_create_user.create_contract;

import com.example.trainingretrofit.model.User;

import id.co.nexsoft.mvp.base.BasePresenter;

public interface ICreatePresenterContract extends BasePresenter {
    void onCreate(User create);
}
