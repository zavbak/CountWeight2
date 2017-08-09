package ru.a799000.alexander.weightcounting.app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alex on 09.08.2017.
 */
public class AppTest {

    @Test
    public void getAppComponent() throws Exception {
        App app = (App)  InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.getAppComponent().getContext();
    }

}