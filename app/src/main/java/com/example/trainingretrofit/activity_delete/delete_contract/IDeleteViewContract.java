package com.example.trainingretrofit.activity_delete.delete_contract;

import com.example.trainingretrofit.model.User;

import id.co.nexsoft.mvp.base.BaseView;

public interface IDeleteViewContract extends BaseView<IDeletePresenterContract> {
    void onDeleteSucces(User user);
}
