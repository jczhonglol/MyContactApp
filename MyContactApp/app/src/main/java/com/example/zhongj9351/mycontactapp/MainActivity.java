package com.example.zhongj9351.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    EditText searchName;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper (this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editAge = (EditText) findViewById(R.id.editText_age);
        searchName = (EditText) findViewById(R.id.editText_search);

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

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0){
            showMessage("Error", "No data found in database");
            //put a Log.d message and toast
            Log.d("MyContact", "No Data found in database");

            int duration = Toast.LENGTH_SHORT;
            Toast noData = Toast.makeText(this, "No Data Found in Databse", duration);
            noData.show();

            return;
        }

        StringBuffer buffer = new StringBuffer();
        //setup loop with Cursor moveToNext method
        //     append each COL to buffer
        //     use getString method

        while(res.moveToNext()){
            buffer.append("ID: " + res.getString(0));
            buffer.append("Name: " + res.getString(1));
            buffer.append("   Address: " + res.getString(2));
            buffer.append("   Age: " + res.getString(3));
            buffer.append("\n\n");

        }
        showMessage("Data", buffer.toString());
        Log.d("MyContact", buffer.toString());



    }

    private String returnSearchContact() {
        String search = searchName.getText().toString();
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //put a Log.d message and toast
            Log.d("MyContact", "No Data found in database");

            int duration = Toast.LENGTH_SHORT;
            Toast noData = Toast.makeText(this, "No Data Found in Database", duration);
            noData.show();

        }

        StringBuffer buffer = new StringBuffer();
        //setup loop with Cursor moveToNext method
        //     append each COL to buffer
        //     use getString method

        while (res.moveToNext()) {
            if (res.getString(1).toUpperCase().equals(search.toUpperCase())) {
                return ("Name: " + res.getString(1)
                        + "    Address: " + res.getString(2)
                        + "    Age: " + res.getString(3));
            }
        }
        return "Not Found";
    }

    public void searchContact (View v){
        showMessage("Result", returnSearchContact());
        Log.d("Result", returnSearchContact());

    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); //cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
