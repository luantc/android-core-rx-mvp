package core.android.presenter;


import core.android.model.event.Event;
import core.android.presenter.contract.MockupBusContract;

import javax.inject.Inject;

import core.android.base.RxPresenter;
import core.android.component.RxBus;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static core.android.model.event.Event.MOCKUP;


public class MockupBusPresenter extends RxPresenter<MockupBusContract.View> implements MockupBusContract.Presenter {

    @Inject
    public MockupBusPresenter() {
    }

    @Override
    public void attachView(MockupBusContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(Event.class)
                .filter(e -> e.getType()==MOCKUP)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(r -> {
                    view.error(r.getMessage());
                }, e -> {
                    view.error(e.getMessage());
                })
        );
    }
}
