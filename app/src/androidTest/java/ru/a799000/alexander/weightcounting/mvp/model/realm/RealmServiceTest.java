package ru.a799000.alexander.weightcounting.mvp.model.realm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import io.realm.Realm;
import ru.a799000.alexander.weightcounting.intities.Product;
import ru.a799000.alexander.weightcounting.mvp.model.realm.servises.RealmService;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by user on 07.08.2017.
 */
public class RealmServiceTest {

    RealmService mRealmService;


    @Before
    public void setUp() throws Exception {

        Context appContext = InstrumentationRegistry.getTargetContext();

        Realm.init(appContext);

        mRealmService = new RealmService();
    }


    @Test
    public void test() throws Exception {

        mRealmService.dellAll()
                .subscribe(() -> {
                    assertTrue(true);
                }, throwable -> {
                    assertTrue(throwable.getMessage(), false);
                });

        mRealmService.getAll()
                .subscribe(products -> {
                    assertTrue(products.size() == 0);
                });


        Product product = Product.getBuilder()
                .name("Товар")
                .build();

        Product product1 = Product.getBuilder()
                .name("Товар 1")
                .build();

        Product product2 = Product.getBuilder()
                .name("Товар 2")
                .build();

        mRealmService.save(product)
                .subscribe(p -> {
                    Log.d("anit", "add: " + p.toString());
                    assertTrue(true);
                });

        mRealmService.save(product1)
                .subscribe(p -> {
                    Log.d("anit", "add: " + p.toString());
                    assertTrue(true);
                });

        mRealmService.save(product2)
                .subscribe(p -> {
                    Log.d("anit", "add: " + p.toString());
                    assertTrue(true);
                });


        mRealmService.getAll()
                .subscribe(products -> {
                    Log.d("anit", "getAll products: " + products.toString());
                    assertTrue(products.size() == 3);
                });

        mRealmService.get(3)
                .subscribe(p -> {
                    Log.d("anit", "id = 3: " + p.toString());
                    assertTrue(true);
                });

        mRealmService.get(28)
                .subscribe(p -> {
                    assertTrue(false);
                }, throwable -> {
                    Log.d("anit", "get(28): " + throwable.getMessage());
                    assertTrue(true);
                }, () -> {
                    assertTrue(false);
                });


        mRealmService.dell(3)
                .subscribe(() -> {
                    Log.d("anit", "dell(3): ");
                    assertTrue(true);
                }, throwable -> {
                    Log.d("anit", "dell(3): " + throwable.getMessage());
                    assertTrue(false);
                });

        mRealmService.dell(28)
                .subscribe(() -> {
                    Log.d("anit", "dell(28): ");
                    assertTrue(false);
                }, throwable -> {
                    Log.d("anit", "dell(28): " + throwable.getMessage());
                    assertTrue(true);
                });


        mRealmService.dellAll()
                .subscribe(() -> {
                    Log.d("anit", "dellALL: ");
                    assertTrue(true);
                }, throwable -> {
                    Log.d("anit", "dellALL: ");
                    assertTrue(false);
                });


    }


}