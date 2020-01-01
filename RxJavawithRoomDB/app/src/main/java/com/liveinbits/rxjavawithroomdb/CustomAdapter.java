package com.liveinbits.rxjavawithroomdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liveinbits.rxjavawithroomdb.model.Employee;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private List<Employee> employeeList;
    public CustomAdapter(List<Employee> employeeList){
        this.employeeList=employeeList;
    }
    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
            holder=new ViewHolder();
            holder.firstname=convertView.findViewById(R.id.firstname);
            holder.lastname=convertView.findViewById(R.id.lastname);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        Employee employee=employeeList.get(position);
        holder.firstname.setText(employee.getFirstname());
        holder.lastname.setText(employee.getLastname());
        return convertView;
    }

    private class ViewHolder{
        TextView firstname,lastname;
    }
}
