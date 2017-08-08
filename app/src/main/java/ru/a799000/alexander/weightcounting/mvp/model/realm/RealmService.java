package ru.a799000.alexander.weightcounting.mvp.model.realm;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ru.a799000.alexander.weightcounting.intities.Product;
import ru.a799000.alexander.weightcounting.mvp.model.realm.migration.RealmMigration;

/**
 * Created by user on 07.08.2017.
 */

public class RealmService implements DBservise {

    Realm mRealm;
    RealmConfiguration mConfig;

    public RealmService() {

        mConfig = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new RealmMigration())
                .build();

    }


    public <E extends RealmModel> long getNextId(Class<E> clazz) {

        long id = 1;

        try {
            id = mRealm.where(clazz).max(RealmTable.ID).intValue() + 1;
        } catch (Exception e) {

        }

        return id;
    }


    @Override
    public Observable<Product> save(Product product) {

        if (mRealm == null || mRealm.isClosed()) {
            mRealm = Realm.getInstance(mConfig);
        }

        return Observable.just(product)
                .doOnSubscribe(disposable -> {
                    mRealm.beginTransaction();
                })
                .doOnComplete(() -> {
                    mRealm.commitTransaction();
                })
                .doOnError(throwable -> {
                    mRealm.cancelTransaction();
                })
                .doFinally(() -> {
                    mRealm.close();
                })
                .map(p -> {

                    if (p.getId() == 0) {
                        p.setId(getNextId(Product.class));
                    }
                    return mRealm.copyToRealmOrUpdate(p);
                });

    }

    @Override
    public Observable<List<Product>> getAll() {

        if (mRealm == null || mRealm.isClosed()) {
            mRealm = Realm.getInstance(mConfig);
        }

        return Observable.just(mRealm.where(Product.class))
                .doOnSubscribe(disposable -> {
                    mRealm.beginTransaction();
                })
                .doOnComplete(() -> {
                    mRealm.commitTransaction();
                })
                .doOnError(throwable -> {
                    mRealm.cancelTransaction();
                })
                .doFinally(() -> {
                    mRealm.close();
                })
                .map(productRealmQuery -> {
                    return productRealmQuery.findAll();
                });
    }

    @Override
    public Observable<Product> get(long id) {


        if (mRealm == null || mRealm.isClosed()) {
            mRealm = Realm.getInstance(mConfig);
        }


        return Observable.just(id)
                .doOnSubscribe(disposable -> {
                    mRealm.beginTransaction();
                })
                .doOnComplete(() -> {
                    mRealm.commitTransaction();
                })
                .doOnError(throwable -> {
                    mRealm.cancelTransaction();
                })
                .doFinally(() -> {
                    mRealm.close();
                })
                .flatMap(aLong -> {
                    try {
                        return Observable.just(mRealm.where(Product.class).equalTo(RealmTable.ID, id).findFirst());
                    } catch (Exception e) {
                        return Observable.error(e);
                    }

                });
    }

    @Override
    public Completable dell(long id) {

        if (mRealm == null || mRealm.isClosed()) {
            mRealm = Realm.getInstance(mConfig);
        }

        try {
            return Completable.fromAction(() -> {
                Product product = mRealm.where(Product.class).equalTo(RealmTable.ID, id).findFirst();
                product.deleteFromRealm();

            })
                    .doOnSubscribe(disposable -> {
                        mRealm.beginTransaction();
                    })
                    .doOnComplete(() -> {
                        mRealm.commitTransaction();
                    })
                    .doOnError(throwable -> {
                        mRealm.cancelTransaction();
                    })
                    .doFinally(() -> {
                        mRealm.close();
                    });

        } catch (Exception e) {
            return Completable.error(e);
        }

    }

    @Override
    public Completable dellAll() {

        if (mRealm == null || mRealm.isClosed()) {
            mRealm = Realm.getInstance(mConfig);
        }

        try {
            return Completable.fromAction(() -> {

                RealmQuery<Product> query = mRealm.where(Product.class);
                RealmResults<Product> results = query.findAll();
                results.deleteAllFromRealm();

            })
                    .doOnSubscribe(disposable -> {
                        mRealm.beginTransaction();
                    })
                    .doOnComplete(() -> {
                        mRealm.commitTransaction();
                    })
                    .doOnError(throwable -> {
                        mRealm.cancelTransaction();
                    })
                    .doFinally(() -> {
                        mRealm.close();
                    });

        } catch (Exception e) {
            return Completable.error(e);
        }
    }


}
