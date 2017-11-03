package core.android.di.component;

import android.app.Activity;

import core.android.view.mockup.activity.MockupActivity;

import core.android.di.module.ActivityModule;
import core.android.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MockupActivity activity);
}
