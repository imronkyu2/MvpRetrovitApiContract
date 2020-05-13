package com.example.trainingretrofit.activity_update_user.update_ui_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trainingretrofit.R;
import com.example.trainingretrofit.activity_create_user.create_ui_activity.CreateUserActivity;
import com.example.trainingretrofit.activity_home.home_ui_activity.MainActivity;
import com.example.trainingretrofit.injection.Injection;
import com.example.trainingretrofit.model.User;
import com.example.trainingretrofit.activity_update_user.update_contract.IUpdatePresenterContract;
import com.example.trainingretrofit.activity_update_user.update_contract.IUpdateViewContract;
import com.example.trainingretrofit.activity_update_user.update_presenter.UpdateUserPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class UpdateActivity extends AppCompatActivity implements IUpdateViewContract {
    TextView tvUserId;
    EditText editTextFirstName, editTextLastName, editTextBirtDate, editTextPassword, editTextConfirmPassword, editTextPhone;
    Button buttonUpdate;
    DatePickerDialog datePickerDialog;
    IUpdatePresenterContract iUpdatePresenterContract;


    public static void start(Context c) {
        Intent intent = new Intent(c, UpdateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        c.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        new UpdateUserPresenter(
                Injection.provideLoginRepository(getApplicationContext()),
                Injection.provideRoleMenuRepository(getApplicationContext()),
                UpdateActivity.this);

        tvUserId = (TextView) findViewById(R.id.userId);
        editTextFirstName = (EditText) findViewById(R.id.etFirstName);
        editTextLastName = (EditText) findViewById(R.id.etLastName);
        editTextBirtDate = (EditText) findViewById(R.id.etBirtDate);
        editTextPassword = (EditText) findViewById(R.id.etpassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.etconfirmPassword);
        editTextPhone = (EditText) findViewById(R.id.etPhone);
        buttonUpdate = (Button) findViewById(R.id.button_update);


        User dataUser = getIntent().getParcelableExtra("User");
        int putuserID = dataUser.getUserId();
        String putfirstName = dataUser.getFirstName();
        String putLastName = dataUser.getLastName();
        long putBirtDate = dataUser.getBirthDate();
        String putPassword = dataUser.getPassword();
        String putConfirm = dataUser.getConfirmPassword();
        final String putPhone = dataUser.getPhone();

        tvUserId.setText(Integer.toString(putuserID));
        editTextFirstName.setText(putfirstName);
        editTextLastName.setText(putLastName);
        editTextBirtDate.setText(Long.toString(putBirtDate));
        editTextPassword.setText(putPassword);
        editTextConfirmPassword.setText(putConfirm);
        editTextPhone.setText(putPhone);

        editTextBirtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        String valueDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
                        editTextBirtDate.setText(valueDate);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    date = (Date) dateFormat.parse(editTextBirtDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


//                int id = Integer.parseInt(tvUserId.getText().toString());
//                long date = Long.parseLong(editTextBirtDate.getText().toString());
//                String fistname = editTextFirstName.getText().toString();
//                String lastname = editTextLastName.getText().toString();
//                String pass = editTextPassword.getText().toString();
//                String confirmpassword = editTextConfirmPassword.getText().toString();
//                String phonee = editTextPhone.getText().toString();
//                User update = new User(id, date, fistname, lastname, pass, confirmpassword, putPhone);
//                iUpdatePresenterContract.onUpdate(update);

                User update = new User();
                update.setUserId(Integer.parseInt(tvUserId.getText().toString()));
                update.setFirstName(editTextFirstName.getText().toString());
                update.setLastName(editTextLastName.getText().toString());
                update.setBirthDate(date.getTime());
                update.setPassword(editTextPassword.getText().toString());
                update.setConfirmPassword(editTextConfirmPassword.getText().toString());
                update.setPhone("+62"+editTextPhone.getText().toString());
                iUpdatePresenterContract.onUpdate(update);

            }
        });

    }

    public void bt_back(View view) {
        finish();
    }


    @Override
    public void onUpdateSucces(User user) {
        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void setPresenter(IUpdatePresenterContract iUpdatePresenterContract) {
        this.iUpdatePresenterContract = iUpdatePresenterContract;

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
