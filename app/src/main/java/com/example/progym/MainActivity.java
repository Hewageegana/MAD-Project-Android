package com.example.progym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword ;
    private Button lognBtn;
    boolean isValid = false;
    String  testUsername = "Kelum";
    String  testPassword = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lognBtn = findViewById(R.id.loginBtn);
        eUsername = (EditText) findViewById(R.id.eUsername);
        ePassword = (EditText) findViewById(R.id.ePassword);

        lognBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = eUsername.getText().toString();
                String Password = ePassword.getText().toString();

                if(Username.isEmpty() || Password.isEmpty()){
                    String errorMessage1 = "Please Enter All the Credentials!";
                    Toast.makeText(MainActivity.this, errorMessage1, Toast.LENGTH_SHORT).show();
                }
                else {

                   isValid =  Validate(Username,Password);

                   if(!isValid){

                       String errorMessage2 = "Incorrect Credentials, Please Try Again!";
                       Toast.makeText(MainActivity.this, errorMessage2, Toast.LENGTH_SHORT).show();
                   }
                   else {

                       String successMessage = "Login Successful!";
                       Toast.makeText(MainActivity.this, successMessage, Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(),Home.class);
                       startActivity(intent);
                   }

                }

            }
        });

    }

    public boolean Validate(String name , String password) {
        if(name.equals(testUsername) && password.equals(testPassword)){
            return  true;
        }
       return false;
    }
}