package com.example.trainingretrofit.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import com.example.trainingretrofit.data_source_api.ApiLink;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

import id.co.nexsoft.mvp.base.BaseEntity;

@Entity(tableName = "USER_YOGI")//Sesuai tabel data base
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Parcelable, BaseEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "USER_ID")
    @JsonProperty("id")//sesuai balikan data base
    private int userID;

    @ColumnInfo(name = "FIRT_NAME")
    @JsonProperty("firstName")
    private String firstName;

    @ColumnInfo(name = "LAST_NAME")
    @JsonProperty("lastName")
    private String lastName;

    @ColumnInfo(name = "BIRT_DATE")
    @JsonProperty("birthDate")
    private long birthDate;

    @ColumnInfo(name = "PASSWORD")
    @JsonProperty("password")
    private String password;

    @ColumnInfo(name = "CONFIRM_PASSWORD")
    @JsonProperty("confirmPassword")
    private String confirmPassword;

    @ColumnInfo(name = "PHONE")
    @JsonProperty("phone")
    private String phone;

    @ColumnInfo(name = "USERNAME")
    @JsonProperty("userName")
    private String username;

    @ColumnInfo(name = "FROM")
    @JsonProperty("from")
    private String from;

    @JsonProperty("Status")
    private int status;

    public User() {

    }

    public int getUserId() {
        return userID;
    }

    public void setUserId(int userId) {
        this.userID = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //Return nilai 0
    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userID);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeLong(this.birthDate);
        dest.writeString(this.password);
        dest.writeString(this.confirmPassword);
        dest.writeString(this.phone);
    }


    public User(Parcel in) {
        this.userID = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.birthDate = in.readLong();
        this.password = in.readString();
        this.confirmPassword = in.readString();
        this.phone = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    @JsonIgnore
    public Serializable getId() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


}
