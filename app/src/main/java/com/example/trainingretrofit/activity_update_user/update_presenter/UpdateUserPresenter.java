package com.example.trainingretrofit.activity_update_user.update_presenter;

import androidx.annotation.NonNull;

import com.example.trainingretrofit.model.User;
import com.example.trainingretrofit.repository.UserRepo;
import com.example.trainingretrofit.activity_update_user.update_contract.IUpdatePresenterContract;
import com.example.trainingretrofit.activity_update_user.update_contract.IUpdateViewContract;

import id.co.nexsoft.mvp.repo.PostCallback;

public class UpdateUserPresenter implements IUpdatePresenterContract {
    private IUpdateViewContract iUpdateViewContract;
    private UserRepo updateUserRepo;

    public UpdateUserPresenter(UserRepo updateUserRepo, Object o, IUpdateViewContract iUpdateViewContract){
        this.iUpdateViewContract = iUpdateViewContract;
        this.updateUserRepo = updateUserRepo;
        iUpdateViewContract.setPresenter(this);
    }


    @Override
    public void onUpdate(@NonNull User user) {
        iUpdateViewContract.showContentLoading();

        updateUserRepo.onUpdate(user, new PostCallback() {
            @Override
            public void onEntityPosted(Object entity) {
                iUpdateViewContract.onUpdateSucces((User) entity);
            }

            @Override
            public void onErrorRequest(Throwable throwable) {
                iUpdateViewContract.hideContentLoading();

            }
        });


    }

    @Override
    public void start(Boolean aBoolean) {

    }

}
