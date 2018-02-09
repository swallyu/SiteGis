package com.mavoic.core.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.mavoic.core.view.BaseView;

/**
 * Created by lzhyu on 2018-2-8.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initImmersionBar();
    }

    protected void initImmersionBar(){
        mImmersionBar  = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mImmersionBar!=null){
            mImmersionBar.destroy();
        }
    }

    protected abstract int getLayoutId();

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void close() {

    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void showProgress(String msg, int progress) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMessage(String msg, String content) {

    }
}
