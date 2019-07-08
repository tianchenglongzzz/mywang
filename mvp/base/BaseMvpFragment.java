package com.lc.myapplication.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * 项目名称：MyModel
 * 创建人：刘
 * 创建时间：2019-07-08 09:07
 * 功能描述：
 */
public abstract class BaseMvpFragment<M extends Model,P extends BasePresenter,V extends View> extends Fragment implements BaseMvp<M,V,P> {
    protected P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter = createPresenter();
        if (presenter != null) {
            presenter.registerModel(createModel());
            presenter.registerView(createView());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter.destroy();
        }
    }
}
