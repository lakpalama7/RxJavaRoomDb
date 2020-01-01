package com.liveinbits.rxjavawithroomdb.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Employee.class, version = 1,exportSchema = false)
public abstract class EmployeeDb extends RoomDatabase {

    public abstract EmployeeDao empdao();
}
