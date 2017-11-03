package core.android.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import core.android.di.component.AppComponent;
import core.android.di.component.DaggerAppComponent;
import core.android.di.module.AppModule;
import core.android.di.module.HttpModule;

import io.realm.Realm;

public class BaseApplication extends Application {

    public static AppComponent mAppComponent;
    private static BaseApplication mInstance;

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

    public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(mInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Realm.init(this);
        Utils.init(this);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
