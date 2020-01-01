package com.liveinbits.rxjavawithroomdb;

import com.liveinbits.rxjavawithroomdb.model.Employee;

import java.util.List;

public interface DataCallBack {
    void addDataCallBack();
    void errorDataCallBack();
    void getEmployees(List<Employee> employeeList);
}
