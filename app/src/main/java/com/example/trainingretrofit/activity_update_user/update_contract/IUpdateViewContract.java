package com.example.trainingretrofit.activity_update_user.update_contract;

import com.example.trainingretrofit.model.User;

import id.co.nexsoft.mvp.base.BaseView;

public interface IUpdateViewContract extends BaseView<IUpdatePresenterContract> {
    void onUpdateSucces(User user);
//    void onUpdateError(String message);
}
