package com.example.trainingretrofit.activity_delete;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trainingretrofit.R;
import com.example.trainingretrofit.activity_delete.delete_contract.IDeletePresenterContract;
import com.example.trainingretrofit.activity_delete.delete_contract.IDeleteViewContract;
import com.example.trainingretrofit.activity_delete.delete_presenter.DeletePresenter;
import com.example.trainingretrofit.activity_home.home_ui_activity.MainActivity;
import com.example.trainingretrofit.injection.Injection;
import com.example.trainingretrofit.model.User;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class DeleteActivity extends AppCompatActivity implements IDeleteViewContract {
    TextView userId, firstName, lastName, birtDate, phone;
    Button deletUser;
    IDeletePresenterContract iDeletePresenterContract;

    public void start(Context c) {
        Intent intent = new Intent(c, DeleteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        c.startActivity(intent);
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

            new DeletePresenter(
                    Injection.provideLoginRepository(getApplicationContext()),
                    Injection.provideRoleMenuRepository(getApplicationContext()),
                    DeleteActivity.this);

            userId = (TextView) findViewById(R.id.tv_userId);
            firstName = (TextView) findViewById(R.id.tv_firstName);
            lastName = (TextView) findViewById(R.id.tv_lastName);
            birtDate = (TextView) findViewById(R.id.tv_birtDate);
            phone = (TextView) findViewById(R.id.tv_Phone);
            deletUser = (Button) findViewById(R.id.delete_user);

            User dataUser = getIntent().getParcelableExtra("DataUser");
            int putuserID = dataUser.getUserId();
            String putfirstName = dataUser.getFirstName();
            String putLastName = dataUser.getLastName();
            long putBirtDate = dataUser.getBirthDate();
            String putPhone = dataUser.getPhone();

            userId.setText(Integer.toString(putuserID));
            firstName.setText(putfirstName);
            lastName.setText(putLastName);
            birtDate.setText(Long.toString(putBirtDate));
            phone.setText(putPhone);


            deletUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = Integer.parseInt(userId.getText().toString());
//                    User deleteUser = new DeleteUser(id);

                    User deleteUser = new User();
                    deleteUser.setUserId(id);
                    iDeletePresenterContract.onDelete(deleteUser);
                }
            });




    }

    @Override
    public void onDeleteSucces(User user) {
        Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void bt_back(View view) {
        finish();
    }

    @Override
    public void setPresenter(IDeletePresenterContract iDeletePresenterContract) {
        this.iDeletePresenterContract = iDeletePresenterContract;

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
