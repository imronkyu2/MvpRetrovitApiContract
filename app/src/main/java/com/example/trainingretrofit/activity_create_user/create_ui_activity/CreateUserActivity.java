package com.example.trainingretrofit.activity_create_user.create_ui_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trainingretrofit.R;
import com.example.trainingretrofit.activity_create_user.create_contract.ICreatePresenterContract;
import com.example.trainingretrofit.activity_create_user.create_contract.ICreateViewContract;
import com.example.trainingretrofit.activity_create_user.create_presenter.CreateUserPresenter;
import com.example.trainingretrofit.activity_home.home_ui_activity.MainActivity;
import com.example.trainingretrofit.injection.Injection;
import com.example.trainingretrofit.model.User;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class CreateUserActivity extends AppCompatActivity implements ICreateViewContract {

    EditText firstName, lastName, birthDate, password, confirmPassword, phone, userId;
    Button buttonCreate;
    DatePickerDialog datePickerDialog;
    ICreatePresenterContract iCreatePresenter;

    public static void start(Context c) {
        Intent intent = new Intent(c, CreateUserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        c.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        new CreateUserPresenter(
                Injection.provideLoginRepository(getApplicationContext()),
                Injection.provideRoleMenuRepository(getApplicationContext()),
                CreateUserActivity.this);

        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        birthDate = (EditText) findViewById(R.id.etBirtDate);
        password = (EditText) findViewById(R.id.etpassword);
        confirmPassword = (EditText) findViewById(R.id.etconfirmPassword);
        phone = (EditText) findViewById(R.id.etPhone);
        buttonCreate = (Button) findViewById(R.id.button_create);


        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(CreateUserActivity.this,new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        String valueDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                        birthDate.setText(valueDate);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    date = (Date) dateFormat.parse(birthDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

//                long dateLong = date.getTime();
//                String fistname = firstName.getText().toString();
//                String lastname = lastName.getText().toString();
//                String pass = password.getText().toString();
//                String confirmpassword = confirmPassword.getText().toString();
//                String phonee = phone.getText().toString();
//                User create = new User(fistname, lastname, dateLong, pass, confirmpassword, phonee);
//                iCreatePresenter.onCreate(create);

                User create = new User();
                create.setFirstName(firstName.getText().toString());
                create.setLastName(lastName.getText().toString());
                create.setBirthDate(date.getTime());
                create.setPassword(password.getText().toString());
                create.setConfirmPassword(confirmPassword.getText().toString());
                create.setPhone("+62"+phone.getText().toString());
                iCreatePresenter.onCreate(create);
            }
        });


    }


    @Override
    public void onCreteSucces(User user) {
        Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void setPresenter(ICreatePresenterContract iCreatePresenter) {
        this.iCreatePresenter = iCreatePresenter;

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

    public void bt_back(View view) {
        finish();
    }
}