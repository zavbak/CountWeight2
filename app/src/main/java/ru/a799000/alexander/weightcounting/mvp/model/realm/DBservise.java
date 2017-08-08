package ru.a799000.alexander.weightcounting.mvp.model.realm;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.a799000.alexander.weightcounting.intities.Product;

/**
 * Created by user on 07.08.2017.
 */

public interface DBservise {
    Observable<Product> save(Product product);
    Observable<List<Product>> getAll();
    Observable<Product> get(long id);
    Completable dell(long id);
    Completable dellAll();

}
