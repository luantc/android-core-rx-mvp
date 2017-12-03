package core.android.view.mockup.activity;

import com.blankj.utilcode.util.FragmentUtils;
import core.android.R;
import core.android.model.entity.mockup.Account;
import core.android.model.entity.mockup.Token;
import core.android.presenter.MockupPresenter;
import core.android.presenter.contract.MockupContract;
import core.android.view.mockup.fragment.MockupFragment;

import core.android.base.BaseActivity;


public class MockupActivity extends BaseActivity<MockupPresenter> implements MockupContract.View {

    @Override
    protected void injectComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mockup_activity;
    }

    @Override
    protected void onViewReady() {
        FragmentUtils.add(getSupportFragmentManager(), MockupFragment.newInstance(),R.id.fl_main);
        presenter.mockup(new Account("peter@klaven", "cityslicka"));
    }

    @Override
    public void success(Token token) {
        presenter.send(token.getToken());
    }
}
