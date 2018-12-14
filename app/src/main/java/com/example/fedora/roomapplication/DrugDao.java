package com.example.fedora.roomapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface DrugDao {

    @Query("SELECT * FROM drug")
    Flowable<List<Drug>> getAllDrugs();



    @Delete
    void deleteDrug(Drug drug);

    @Insert
    void insertDrug(Drug drug);
}
