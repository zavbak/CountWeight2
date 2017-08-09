package ru.a799000.alexander.weightcounting.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.a799000.alexander.weightcounting.di.modules.AppModule;

/**
 * Created by Alex on 09.08.2017.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();
}
