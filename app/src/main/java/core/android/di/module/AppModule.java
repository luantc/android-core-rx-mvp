package core.android.di.module;

import core.android.retrofit.APIs;
import core.android.retrofit.RetrofitClient;

import javax.inject.Singleton;

import core.android.base.BaseApplication;
import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitClient provideRetrofitClient(APIs apis) {
        return new RetrofitClient(apis);
    }
}
