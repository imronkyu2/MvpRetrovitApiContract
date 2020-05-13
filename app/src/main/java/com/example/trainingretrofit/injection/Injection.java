package com.example.trainingretrofit.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.trainingretrofit.model.User;
import com.example.trainingretrofit.repository.UserRepo;
import com.example.trainingretrofit.repository.UserRepoImp;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {

    public static UserRepo provideLoginRepository(@NonNull Context context) {
        checkNotNull(context);
        // todo getSharedPreferences
        return UserRepoImp.getInstance(context);
    }

    public static Object provideRoleMenuRepository(@NonNull Context applicationContext) {
        return null;
    }

    public static UserRepo provideUserRepository(@NonNull Context context){
        checkNotNull(context);
        // todo getSharedPreferences
        return UserRepoImp.getInstance(context);
    }
}
