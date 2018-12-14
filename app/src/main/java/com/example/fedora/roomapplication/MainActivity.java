package com.example.fedora.roomapplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.jakewharton.rxbinding3.view.RxView;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_insert_drug)
    Button insertButton;

    @BindView(R.id.rv_drugs)
    RecyclerView drugsRecycler;

    RecyclerView.Adapter adapter;

    List<Drug> drugList;



    private DrugDatabase drugDatabase;

    Disposable subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding butter knife to this activity
        ButterKnife.bind(this);

        drugList = new ArrayList<>();
        adapter = new DrugAdapter(drugList);
        drugsRecycler.setLayoutManager(new LinearLayoutManager(this));
        drugsRecycler.setHasFixedSize(true);
        drugsRecycler.setAdapter(adapter);

        drugDatabase = Room.databaseBuilder(getApplicationContext(),
                DrugDatabase.class, Consts.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        Maybe<Drug> drugFlowable = drugDatabase.drugDao().getAllDrugs().flatMap(new Function<List<Drug>, MaybeSource<Drug>>() {
            @Override
            public MaybeSource<Drug> apply(List<Drug> drugs) throws Exception {
                return null;
            }
        });

        subscription = drugFlowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Drug>() {
                    @Override
                    public void accept(Drug drug) throws Exception {
                        drugList.add(drug);
                        adapter.notifyItemInserted(drugList.size()-1);
                    }
                });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Drug> drugs = drugDatabase.drugDao().getAllDrugs();
//                for (Drug drug : drugs){
//
//                }
//            }
//        }).start();



        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , DrugInsertionActivity.class);
                startActivity(intent);
            }
        });


    }
}
