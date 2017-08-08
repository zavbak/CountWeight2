package ru.a799000.alexander.weightcounting.mvp.model.realm;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import io.realm.Realm;
import ru.a799000.alexander.weightcounting.intities.Barcode;
import ru.a799000.alexander.weightcounting.intities.Product;

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
                    Log.d("anit","add: " + p.toString());
                    assertTrue(true);
                });

        mRealmService.save(product1)
                .subscribe(p -> {
                    Log.d("anit","add: " + p.toString());
                    assertTrue(true);
                });

        mRealmService.save(product2)
                .subscribe(p -> {
                    Log.d("anit","add: " + p.toString());
                    assertTrue(true);
                });


        mRealmService.getAll()
                .subscribe(products -> {
                    Log.d("anit","products: " + products.toString());
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
                    Log.d("anit", "id = 28: " + throwable.getMessage());
                    assertTrue(true);
                }, () -> {
                    assertTrue(false);
                });


        mRealmService.dell(3)
                .subscribe(() -> {
                    assertTrue(true);
                },throwable -> {
                    Log.d("anit", "id = 28: " + throwable.getMessage());
                    assertTrue(false);
                });
//
//
//        mRealmService.get(28)
//                .subscribe(p -> {
//                    assertTrue(false);
//                }, throwable -> {
//                    Log.d("anit", "id = 28: " + throwable.getMessage());
//                    assertTrue(true);
//                }, () -> {
//                    assertTrue(false);
//                });





    }


    public void save() throws Exception {

        Product product = Product.getBuilder()
                .name("Товар")
                .build();


        Barcode barcode = Barcode.getBuilder().setBarcode("222").build();


        mRealmService.save(product)
                .subscribe(p -> {

                    assertEquals(p.getName(), product.getName());

                });

    }


    public void getAll() throws Exception {
        mRealmService.getAll()
                .subscribe(products -> {
                    assertTrue(products.size() != 0);

                }, throwable -> {
                }, () -> {
                    assertTrue(true);
                });

    }


    public void get() throws Exception {
        mRealmService.get(28)
                .subscribe(p -> {
                    assertTrue(true);
                }, throwable -> {
                    assertTrue(true);
                }, () -> {
                    assertTrue(true);
                });

    }


    public void dell() throws Exception {
        mRealmService.dell(2)
                .subscribe(() -> {
                    assertTrue(true);

                }, throwable -> {
                    assertTrue(true);
                });
    }


    public void dellAll() throws Exception {
        mRealmService.dellAll()
                .subscribe(() -> {
                    assertTrue(true);

                }, throwable -> {
                    assertTrue(true);
                });
    }

}