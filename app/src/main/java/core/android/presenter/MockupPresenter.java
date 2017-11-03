package core.android.presenter;


import com.blankj.utilcode.util.NetworkUtils;

import javax.inject.Inject;

import core.android.base.RxPresenter;
import core.android.component.RxBus;
import core.android.model.entity.mockup.Account;
import core.android.model.event.Event;
import core.android.presenter.contract.MockupContract;
import core.android.retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static core.android.model.event.Event.MOCKUP;


public class MockupPresenter extends RxPresenter<MockupContract.View> implements MockupContract.Presenter {

    private RetrofitClient retrofit;

    @Inject
    public MockupPresenter(RetrofitClient retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void send(String message) {
        RxBus.getDefault().post(new Event(message, MOCKUP));
    }

    @Override
    public void mockup(Account account) {
        if (NetworkUtils.isConnected()) {
            view.loading();
            addSubscribe(retrofit.mockup(account)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(r -> {
                        view.hide();
                        view.success(r);
                    }, e -> {
                        view.hide();
                        view.error(e.getMessage());
                    })
            );
        } else {
        }
    }
}
