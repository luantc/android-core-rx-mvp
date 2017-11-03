package core.android.di.component;

import android.app.Activity;

import core.android.view.mockup.fragment.MockupFragment;

import core.android.di.module.FragmentModule;
import core.android.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


    void inject(MockupFragment fragment);
}
