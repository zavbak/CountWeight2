package ru.a799000.alexander.weightcounting.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.a799000.alexander.weightcounting.app.App;

/**
 * Created by Alex on 09.08.2017.
 */
@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return app;
    }
}
