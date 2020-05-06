package com.baiheng.rxjavastudy;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    Integer i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//            }
//        }).flatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
//                List<String> list = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    list.add("I am value " + integer);
//                }
//                int delayTime = (int) (1 + Math.random() * 10);
//                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
//            }
//        }).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(@NonNull String s) throws Exception {
//                        Log.e(TAG, "flatMap : accept : " + s + "\n");
////                        mRxOperatorsText.append("flatMap : accept : " + s + "\n");
//                    }
//                });

        Observable.create(new ObservableOnSubscribe < Integer > () {

            @Override
            public void subscribe(ObservableEmitter < Integer > e) throws Exception {
                e.onComplete();
            }
        })
                .defaultIfEmpty(666)
                .subscribe(new Consumer < Integer > () {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "========================onNext " + integer);
                    }
                });




























































    }

}
