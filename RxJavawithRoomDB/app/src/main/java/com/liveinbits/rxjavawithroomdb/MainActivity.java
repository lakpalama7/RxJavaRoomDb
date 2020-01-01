package com.liveinbits.rxjavawithroomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.liveinbits.rxjavawithroomdb.datarepository.DataManager;
import com.liveinbits.rxjavawithroomdb.model.Employee;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DataCallBack{


    private ListView listView;
    private Button insert,display;
    private DataManager dataManager;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        insert=findViewById(R.id.insert);
        display=findViewById(R.id.display);
        dataManager=DataManager.getInstance(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.addData(MainActivity.this,"lakpa","sherpa");
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.getEmployeeData(MainActivity.this);
            }
        });


    }

    @Override
    public void addDataCallBack() {
        Toast.makeText(MainActivity.this,"Insert success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorDataCallBack() {
        Toast.makeText(MainActivity.this,"Erorr ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void getEmployees(List<Employee> employeeList) {
            adapter=new CustomAdapter(employeeList);
            listView.setAdapter(adapter);
    }
}
