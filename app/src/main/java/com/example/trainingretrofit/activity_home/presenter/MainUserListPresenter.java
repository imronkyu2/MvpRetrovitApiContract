package com.example.trainingretrofit.activity_home.presenter;

import com.example.trainingretrofit.activity_home.home_contract.IAdapterPresenterContract;
import com.example.trainingretrofit.activity_home.home_contract.IAdapterViewContract;
import com.example.trainingretrofit.activity_home.home_ui_activity.MainActivity;
import com.example.trainingretrofit.model.User;
import com.example.trainingretrofit.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

import id.co.nexsoft.mvp.repo.GetCallback;
import id.co.nexsoft.mvp.repo.LoadCallback;
import id.co.nexsoft.mvp.repo.PostCallback;

public class MainUserListPresenter implements IAdapterPresenterContract {
    private UserRepo mRepo;
    private IAdapterViewContract mView;


    public MainUserListPresenter(UserRepo provideUserRepository, IAdapterViewContract viewContract) {
        this.mRepo = provideUserRepository;
        this.mView = viewContract;
        viewContract.setPresenter(this);

    }

    @Override
    public void getCartUserList() {
        mView.showContentLoading();
        mRepo.getAllUserList(new LoadCallback() {
            @Override
            public void onLoadedData(Object o) {
                mView.doUserListSuccessfully((List<User>) o);
                mView.hideContentLoading();
            }

            @Override
            public void onDataNotAvailable() {
                mView.doUserListSuccessfully(new ArrayList<User>());
                mView.hideContentLoading();

            }

            @Override
            public void onErrorRequest(Throwable throwable) {
                mView.hideContentLoading();
            }
        });

    }

    @Override
    public void removeCartItem(final User user) {
        mView.showContentLoading();
        mRepo.getUserId(new GetCallback() {
            @Override
            public void onEntityLoaded(Object o) {
                user.setUserId((int) o);
                mRepo.doRemove(user, new PostCallback() {
                    @Override
                    public void onEntityPosted(Object o) {
                        mView.doRemoveUserSuccess(user.getUserId());
                        mView.hideContentLoading();
                    }

                    @Override
                    public void onErrorRequest(Throwable throwable) {
                        mView.doRemoveUserSuccess(user.getUserId());
                        mView.hideContentLoading();

                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                mView.doRemoveUserFailed(user.getUserId());
                mView.hideContentLoading();

            }

            @Override
            public void onErrorRequest(Throwable throwable) {
                mView.doRemoveUserFailed(user.getUserId());
                mView.hideContentLoading();

            }
        });

    }

    @Override
    public void start(Boolean aBoolean) {

    }
}
