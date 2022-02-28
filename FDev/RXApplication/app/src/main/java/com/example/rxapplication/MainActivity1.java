package com.example.rxapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class MainActivity1 extends AppCompatActivity {

    private TextView tvRx;
    private Observable<String> observable;

    String label = "Greeting from Hello !!!";
    private Disposable disposable;
//    private DisposableObserver<String> disposableObserver;
    private String TAG = this.getClass().getSimpleName();

    private DisposableObserver<String> observer2;
    private DisposableObserver<String> observer;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRx = findViewById(R.id.tv_rx);



        observable = Observable.just(label);

//        observable.subscribeOn(Schedulers.io());
//        observable.subscribeOn(AndroidSchedulers.mainThread());




//       observer = new Observer<String>() {
//           @Override
//           public void onSubscribe(@NonNull Disposable d) {
////               disposable = d;
//           }
//
//           @Override
//           public void onNext(@NonNull String s) {
//               tvRx.setText(s);
//           }
//
//           @Override
//           public void onError(@NonNull Throwable e) {
//
//           }
//
//           @Override
//           public void onComplete() {
//
//           }
//       };

        observer = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.v(TAG,"onNext");
                tvRx.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.v(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.v(TAG,"onComplete");
            }
        };

        observer2 = new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                Log.v(TAG,"onNext");
                tvRx.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.v(TAG,"onError");
            }

            @Override
            public void onComplete() {
                Log.v(TAG,"onComplete");
            }
        };



//        observable.subscribe(disposableObserver);

//
//        observer2 = new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
////               disposable = d;
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//                tvRx.setText(s);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };



        compositeDisposable.add(observer);
        compositeDisposable.add(observer2);


        observable.subscribe(observer);
        observable.subscribe(observer2);



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(disposable!=null){
//            disposable.dispose();
//            disposableObserver.dispose();

//            observer2.dispose();
//            observer.dispose();

            compositeDisposable.clear();


        }
    }
}