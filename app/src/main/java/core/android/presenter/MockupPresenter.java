package core.android.presenter;


import android.app.Activity;

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
    private Activity activity;

    @Inject
    public MockupPresenter(RetrofitClient retrofit, Activity a) {
        this.retrofit = retrofit;
        this.activity = a;
    }

    @Override
    public void send(String message) {
        RxBus.getDefault().post(new Event(message, MOCKUP));
    }

    @Override
    public void mockup(Account account) {
        if (NetworkUtils.isConnected()) {
            view.showLoadingDialog("Hello");
            addSubscribe(retrofit.mockup(account)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(r -> {
                        view.hideLoadingDialog();
                        view.success(r);
                    }, e -> {
                        view.hideLoadingDialog();
                        view.onError(e.getMessage());
                    })
            );
        } else {
        }
    }
}
