package com.liveinbits.rxjavawithroomdb.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface EmployeeDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void EmployeeInsert(Employee employee);

    @Query("select * from employee")
    Flowable<List<Employee>> getEmployeeList();
}
