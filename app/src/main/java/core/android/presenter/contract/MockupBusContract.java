package core.android.presenter.contract;

import core.android.base.BasePresenter;
import core.android.base.BaseView;


public interface MockupBusContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
