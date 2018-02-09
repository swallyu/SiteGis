package com.mavoic.core.activitys;

import android.support.v4.app.Fragment;

import com.mavoic.core.view.BaseView;

/**
 * Created by lzhyu on 2018-2-9.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

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
