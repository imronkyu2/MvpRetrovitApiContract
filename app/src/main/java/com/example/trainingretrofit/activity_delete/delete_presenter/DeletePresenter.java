package com.example.trainingretrofit.activity_delete.delete_presenter;

import androidx.annotation.NonNull;

import com.example.trainingretrofit.activity_delete.delete_contract.IDeletePresenterContract;
import com.example.trainingretrofit.activity_delete.delete_contract.IDeleteViewContract;
import com.example.trainingretrofit.model.User;
import com.example.trainingretrofit.repository.UserRepo;

import id.co.nexsoft.mvp.repo.PostCallback;

public class DeletePresenter implements IDeletePresenterContract {
    private IDeleteViewContract iDeleteViewContract;
    private UserRepo deleteUserRepo;

    public DeletePresenter(UserRepo deleteUserRepo, Object o, IDeleteViewContract iDeleteViewContract){
        this.iDeleteViewContract = iDeleteViewContract;
        this.deleteUserRepo = deleteUserRepo;
        iDeleteViewContract.setPresenter(this);
    }

    @Override
    public void onDelete(@NonNull User user) {
        iDeleteViewContract.showContentLoading();
        deleteUserRepo.onDelete(user, new PostCallback() {
            @Override
            public void onEntityPosted(Object entity) {
                iDeleteViewContract.onDeleteSucces((User) entity);
            }

            @Override
            public void onErrorRequest(Throwable throwable) {
                iDeleteViewContract.hideContentLoading();

            }
        });
    }

    @Override
    public void start(Boolean aBoolean) {

    }
}
