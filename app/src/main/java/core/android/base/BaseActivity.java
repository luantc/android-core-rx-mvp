package core.android.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import core.android.R;
import core.android.di.component.ActivityComponent;
import core.android.di.component.DaggerActivityComponent;
import core.android.di.module.ActivityModule;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected T presenter;
    protected Activity context;
    private Unbinder unbinder;
    protected FrameLayout fl_main;
    @BindView(R.id.fl_pb)
    public FrameLayout fl_pb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.base_layout);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(getLayoutId(), fl_main, false);
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        fl_main.addView(view);
        unbinder = ButterKnife.bind(this);
        initInject();
        if (presenter != null)
            presenter.attachView(this);
        initEventAndData();
    }

    public void loading() {
        fl_pb.setVisibility(View.VISIBLE);
    }

    public void hide() {
        fl_pb.setVisibility(View.GONE);
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.detachView();
        unbinder.unbind();
    }

    /**
     * This method is for injecting activity component used in the activity
     */
    protected abstract void initInject();

    /**
     * This method is for initializing layout used in the activity.
     */
    protected abstract int getLayoutId();

    /**
     * This method is for initializing data after layout is loaded.
     */
    protected abstract void initEventAndData();
}