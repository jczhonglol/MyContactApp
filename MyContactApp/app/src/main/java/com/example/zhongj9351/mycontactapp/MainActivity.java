package com.example.zhongj9351.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editAge;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper (this);

        editName = (EditText) findViewById(R.id.editText_name);
        editName = (EditText) findViewById(R.id.editText_address);
        editName = (EditText) findViewById(R.id.editText_age);

    }


    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editAge.getText().toString());
        int duration = Toast.LENGTH_SHORT;
        Toast toastSuccessful = Toast.makeText(this, "Data insertion successful", duration);
        Toast toastUnsuccessful = Toast.makeText(this, "Data insertion unsuccessful", duration);
        if(isInserted == true){
            Log.d("My Contact", "Data  insertion successful");
            //create toast message to user indicating data inserted correctly
            toastSuccessful.show();
        }
        else{
            Log.d("My Contact", "Data  insertion NOT successful");
            //create toast message to user indicating data inserted incorrectly
            toastUnsuccessful.show();
        }
    }


}
