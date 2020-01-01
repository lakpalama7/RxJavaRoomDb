package com.liveinbits.rxjavawithroomdb.datarepository;

import android.content.Context;

import androidx.room.Room;

import com.liveinbits.rxjavawithroomdb.DataCallBack;
import com.liveinbits.rxjavawithroomdb.model.Employee;
import com.liveinbits.rxjavawithroomdb.model.EmployeeDb;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class DataManager {

    private Context context;
    private static EmployeeDb employeeDb;
    private static DataManager dataManager;
    private DataManager(Context context){
        this.context=context;
        employeeDb= Room.databaseBuilder(context,EmployeeDb.class,"employee.db").build();

    }
    public static DataManager getInstance(Context context){
        if(dataManager==null){
            dataManager=new DataManager(context);
        }
        return dataManager;
    }

    public void addData(final DataCallBack dataCallBack,final String firstname,final String lastname){

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                employeeDb.empdao().EmployeeInsert(new Employee(firstname,lastname));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                            dataCallBack.addDataCallBack();
                    }

                    @Override
                    public void onError(Throwable e) {
                            dataCallBack.errorDataCallBack();
                    }
                });
    }
    public void getEmployeeData(final DataCallBack dataCallBack){

        employeeDb.empdao().getEmployeeList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Employee>>() {
                    @Override
                    public void accept(List<Employee> employees) throws Exception {
                        dataCallBack.getEmployees(employees);
                    }
                });
    }


}
