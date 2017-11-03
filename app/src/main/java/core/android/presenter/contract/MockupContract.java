package core.android.presenter.contract;

import core.android.model.entity.mockup.Account;
import core.android.model.entity.mockup.Token;

import core.android.base.BasePresenter;
import core.android.base.BaseView;


public interface MockupContract {
    interface View extends BaseView {
        void success(Token token);
    }

    interface Presenter extends BasePresenter<View> {
        void send(String message);
        void mockup(Account account);
    }
}
