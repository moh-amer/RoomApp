package com.example.fedora.roomapplication;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Drug {

    @PrimaryKey(autoGenerate = true)
    private int drug_id;

    @ColumnInfo(name = "drug_name")
    private String drugName;

    @ColumnInfo(name = "drug_price")
    private double drugPrice;

    public int getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public double getDrugPrice() {
        return drugPrice;
    }

    public void setDrugPrice(double drugPrice) {
        this.drugPrice = drugPrice;
    }
}
