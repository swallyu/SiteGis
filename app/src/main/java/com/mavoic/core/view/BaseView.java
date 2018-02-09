package com.mavoic.core.view;

/**
 * Created by lzhyu on 2018-2-9.
 */

public interface BaseView {

    void showMessage(String msg);

    void close();

    void showProgress(String msg);

    void showProgress(String msg, int progress);

    void hideProgress();

    void showErrorMessage(String msg,String content);
}
