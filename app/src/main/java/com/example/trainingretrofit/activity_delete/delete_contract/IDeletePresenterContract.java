package com.example.trainingretrofit.activity_delete.delete_contract;

import com.example.trainingretrofit.model.User;

import id.co.nexsoft.mvp.base.BasePresenter;

public interface IDeletePresenterContract extends BasePresenter {
    void onDelete(User user);
}
