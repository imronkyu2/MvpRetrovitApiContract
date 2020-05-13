package com.example.trainingretrofit.activity_home.home_contract;

import com.example.trainingretrofit.model.User;

import id.co.nexsoft.mvp.base.BasePresenter;

public interface IAdapterPresenterContract extends BasePresenter {
    void getCartUserList();
    void removeCartItem(User user);

}
