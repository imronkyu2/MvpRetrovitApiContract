package com.example.trainingretrofit.activity_home.home_adapter;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingretrofit.R;
import com.example.trainingretrofit.activity_delete.DeleteActivity;
import com.example.trainingretrofit.activity_home.home_contract.IAdapterPresenterContract;
import com.example.trainingretrofit.activity_home.home_contract.IAdapterViewContract;
import com.example.trainingretrofit.activity_home.home_ui_activity.MainActivity;
import com.example.trainingretrofit.activity_update_user.update_ui_activity.UpdateActivity;
import com.example.trainingretrofit.data_source_api.ApiLink;
import com.example.trainingretrofit.model.User;

import java.util.ArrayList;
import java.util.List;

import id.co.nexsoft.mvp.base.FoundamentalActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> implements IAdapterViewContract {
    private List<User> my_list;
    private Context context;
    private User userList;
    private IAdapterViewContract adapterViewContract;


    public MainAdapter(Context context, List<User> my_list, IAdapterViewContract adapterViewContract) {
        this.my_list = my_list;
        this.context = context;
        this.adapterViewContract = adapterViewContract;
    }


    @NonNull
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_user_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,int position) {
//        userList = my_list.get(i);
//        int modPaging = (i + 1) % ApiLink.PAGE_SIZE.USER_LIST;
//        if (modPaging == 0){
//            adapterViewContract.doGetNextData(i+1);
//        }
        final User userList = my_list.get(position);
        holder.firstNameText.setText(userList.getFirstName());
        holder.lastNameText.setText(userList.getLastName());
        holder.birthDateText.setText(Long.toString(userList.getBirthDate()));
        holder.phoneText.setText(userList.getPhone());
        //Button Update
        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("User", userList);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delete = new Intent(context, DeleteActivity.class);
                delete.putExtra("DataUser", userList);
                delete.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(delete);
            }
        });

    }


    @Override
    public int getItemCount() {
        return (null != my_list ? my_list.size() : 0);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView firstNameText, lastNameText, birthDateText, phoneText;
        Button buttonUpdate, buttonDelete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameText = itemView.findViewById(R.id.firstName_text);
            lastNameText = itemView.findViewById(R.id.lastName_text);
            birthDateText = itemView.findViewById(R.id.birthDate_text);
            phoneText = itemView.findViewById(R.id.phone_text);

            buttonUpdate = itemView.findViewById(R.id.button_update);
            buttonDelete = itemView.findViewById(R.id.button_delete);
        }
    }



    public void updateList(List<User> user) {
        this.my_list = new ArrayList<User>();
        this.my_list.addAll(user);
        this.notifyDataSetChanged();

    }

    public List<User> getItemsList() {
        return this.my_list;
    }

    @Override
    public void doGetNextData(int position) {

    }

    @Override
    public void doUserListSuccessfully(List<User> users) {

    }

    @Override
    public void doRemoveUserSuccess(int userId) {

    }

    @Override
    public void doRemoveUserFailed(int userId) {

    }

    @Override
    public void setPresenter(IAdapterPresenterContract iAdapterPresenterContract) {

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