package core.android.di.component;

import core.android.base.BaseApplication;

import core.android.di.module.AppModule;
import core.android.di.module.HttpModule;
import core.android.retrofit.RetrofitClient;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    BaseApplication getContext();

    RetrofitClient retrofitClient();
}
