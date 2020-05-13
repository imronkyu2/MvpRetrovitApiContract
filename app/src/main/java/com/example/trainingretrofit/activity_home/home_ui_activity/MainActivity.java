package com.example.trainingretrofit.activity_home.home_ui_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trainingretrofit.activity_create_user.create_ui_activity.CreateUserActivity;
import com.example.trainingretrofit.R;
import com.example.trainingretrofit.activity_home.home_adapter.MainAdapter;
import com.example.trainingretrofit.activity_home.home_contract.IAdapterPresenterContract;
import com.example.trainingretrofit.activity_home.home_contract.IAdapterViewContract;
import com.example.trainingretrofit.activity_home.presenter.MainUserListPresenter;
import com.example.trainingretrofit.injection.Injection;
import com.example.trainingretrofit.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements List<User>, IAdapterViewContract {

    private RecyclerView recyclerView;
//    List<User> main_list;
    private IAdapterPresenterContract mPresenter;
    private MainAdapter userAdapter;
    private Integer lastPosition = -1;
    private Boolean callData = true;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewUserLiust);
        new MainUserListPresenter(Injection.provideUserRepository(getApplicationContext()), this);
    }

    @Override
    public void doGetNextData(int position) {

    }

    @Override
    public void doUserListSuccessfully(List<User> users) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new MainAdapter(getApplicationContext(), users, this);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void doRemoveUserSuccess(int userId) {

    }

    @Override
    public void doRemoveUserFailed(int userId) {

    }


    public void bt_create(View view) {
        Intent create = new Intent(MainActivity.this, CreateUserActivity.class);
        startActivity(create);
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return false;
    }

    @NonNull
    @Override
    public Iterator<User> iterator() {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return null;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends User> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends User> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public User get(int index) {
        return null;
    }

    @Override
    public User set(int index, User element) {
        return null;
    }

    @Override
    public void add(int index, User element) {

    }

    @Override
    public User remove(int index) {
        return null;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return 0;
    }

    @NonNull
    @Override
    public ListIterator<User> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<User> listIterator(int index) {
        return null;
    }

    @NonNull
    @Override
    public List<User> subList(int fromIndex, int toIndex) {
        return null;
    }


    @Override
    public void setPresenter(IAdapterPresenterContract iAdapterPresenterContract) {

        this.mPresenter = iAdapterPresenterContract;
        mPresenter.getCartUserList();
    }

    @Override
    public void showConnectionError() {

    }

    @Override
    public void showContentLoading() {

    }

    @Override
    public void hideContentLoading() {

    }

    @Override
    public void showConnectionFailed() {

    }
}
