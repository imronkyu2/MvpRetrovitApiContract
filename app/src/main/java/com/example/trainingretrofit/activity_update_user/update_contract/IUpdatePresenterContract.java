package com.example.trainingretrofit.activity_update_user.update_contract;

import com.example.trainingretrofit.model.User;

import id.co.nexsoft.mvp.base.BasePresenter;

public interface IUpdatePresenterContract extends BasePresenter {
    void onUpdate(User user);
}
