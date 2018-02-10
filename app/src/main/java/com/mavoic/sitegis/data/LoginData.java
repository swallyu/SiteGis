package com.mavoic.sitegis.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mavoic.sitegis.BR;

/**
 * Created by lzhyu on 2018-2-9.
 */

public class LoginData extends BaseObservable{

    private String userName;

    private String password;

    @Bindable
    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
