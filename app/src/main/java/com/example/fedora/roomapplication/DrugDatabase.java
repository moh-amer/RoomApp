package com.example.fedora.roomapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = Drug.class , version = 1 ,exportSchema = false)
public abstract class DrugDatabase extends RoomDatabase {
    public abstract DrugDao drugDao() ;
}
