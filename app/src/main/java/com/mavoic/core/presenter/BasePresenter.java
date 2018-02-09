package com.mavoic.core.presenter;

import com.mavoic.core.view.BaseView;

/**
 * Created by lzhyu on 2018-2-9.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T View);

    void detachView();

}
