package com.example.comp1424;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.RadioButton;

import android.widget.RadioGroup;
import android.widget.Toast;

import java.security.acl.Group;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditHike extends Fragment {
    Button button;
    EditText name, location, date, length, observation;
    RadioButton radio,radio2;
    RadioGroup group;
    int year;
    int month;
    int day;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_hike, container, false);
        initView(view);
        Calendar calendar=Calendar.getInstance();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Added succesfully", Toast.LENGTH_SHORT).show();
                String nam=name.getText().toString();
                String loc=location.getText().toString();
                String dat=date.getText().toString();
                String len=length.getText().toString();
                String obs=observation.getText().toString();

                DbHandler dbHandler = new DbHandler(getActivity());
                dbHandler.insertUserDetails(nam,loc,dat,len,obs);

                if(nam.isEmpty()){
                    name.requestFocus();
                    name.setError("Enter your name");
                }
                else if(loc.isEmpty()){
                    location.requestFocus();
                    location.setError("Please enter location");
                }
                else if(dat.isEmpty()){
                    date.requestFocus();
                    date.setError("Please select the date");
                }
                else if(len.isEmpty()) {
                    length.requestFocus();
                    length.setError("Please enter the length");
                }


                else if(!radio.isChecked()&&!radio2.isChecked()){
                    Toast.makeText(getContext(), "Select parking available", Toast.LENGTH_LONG).show();
                    return;


                }
                else{
                    Toast.makeText(getActivity(), "register succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),DetailsActivity.class);
                    startActivity(intent);
                }




            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));


                    }
                },year, month, day);
                datePickerDialog.show();



            }
        });

        return view;

    }




    private void initView(View view){
        name=(EditText) view.findViewById(R.id.name);
        location=(EditText) view.findViewById(R.id.location);
        date=(EditText) view.findViewById(R.id.date);
        length=(EditText) view.findViewById(R.id.length);
        observation=(EditText) view.findViewById(R.id.observation);
        button=(Button)view.findViewById(R.id.button);
        radio=(RadioButton) view.findViewById(R.id.radio);
        radio2=(RadioButton) view.findViewById(R.id.radio2);
        group=(RadioGroup) view.findViewById(R.id.group);



    }


}
