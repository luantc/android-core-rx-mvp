package core.android.view.mockup.fragment;


import android.support.v4.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import core.android.R;
import core.android.presenter.MockupBusPresenter;
import core.android.presenter.contract.MockupBusContract;

import core.android.base.BaseFragment;

public class MockupFragment extends BaseFragment<MockupBusPresenter> implements MockupBusContract.View {

    public static Fragment newInstance(){
        return new MockupFragment();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mockup_fragment;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void error(String error) {
        ToastUtils.showLong(error);
    }
}
