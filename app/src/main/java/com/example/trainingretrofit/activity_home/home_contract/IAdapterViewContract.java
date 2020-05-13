package com.example.trainingretrofit.activity_home.home_contract;

import com.example.trainingretrofit.activity_create_user.create_contract.ICreatePresenterContract;
import com.example.trainingretrofit.model.User;

import java.util.List;

import id.co.nexsoft.mvp.base.BaseView;

public interface IAdapterViewContract extends  BaseView<IAdapterPresenterContract>{
    void doGetNextData(int position);
    void doUserListSuccessfully(List<User> users);
    void doRemoveUserSuccess(int userId);

    void doRemoveUserFailed(int userId);
}
