package com.example.fedora.roomapplication;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrugInsertionActivity extends AppCompatActivity {


    @BindView(R.id.btn_complete_insertion)
    Button completeInsButton;

    @BindView(R.id.et_drug_name)
    EditText drugNameEtext;

    @BindView(R.id.et_drug_price)
    EditText drugPriceEtext;

    @BindView(R.id.auto_text)
    AutoCompleteTextView autoCompleteTextView;

    DrugDatabase drugDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_insertion);
        //Bind ButterKnife to this activity

        ButterKnife.bind(this);

        String[] names = {"Ahmed" ,"Ahd" , "Adel" ,"Aser" ,"Aseel","Adm"};

        drugDatabase = Room.databaseBuilder(getApplicationContext(),
                DrugDatabase.class, Consts.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        completeInsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String drugName = drugNameEtext.getText().toString();
                int drugPrice = Integer.valueOf(drugPriceEtext.getText().toString());
                final Drug drug = new Drug();

                drug.setDrugName(drugName);
                drug.setDrugPrice(drugPrice);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        drugDatabase.drugDao().insertDrug(drug);
                    }
                }).start();
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);

        autoCompleteTextView.setAdapter(adapter);
    }
}
