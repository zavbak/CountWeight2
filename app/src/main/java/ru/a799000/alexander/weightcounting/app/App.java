package ru.a799000.alexander.weightcounting.app;

import android.app.Application;

import ru.a799000.alexander.weightcounting.di.components.AppComponent;
import ru.a799000.alexander.weightcounting.di.components.DaggerAppComponent;
import ru.a799000.alexander.weightcounting.di.modules.AppModule;

/**
 * Created by user on 07.08.2017.
 */

public class App extends Application{


    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger();
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
