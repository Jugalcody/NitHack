package com.example.sahaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    TextView signup;
    Spinner spinner;
    String user="null";
    AppCompatButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup=findViewById(R.id.regclick);
        spinner=findViewById(R.id.loginSpinner);
        login =findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.equals("Student") || user.equals("Faculity Member")){
                    Intent i=new Intent(getApplicationContext(), HomePage_General.class);
                    Toast.makeText(getApplicationContext(),"Welcome "+user,Toast.LENGTH_SHORT).show();
                    startActivity(i);

                }
                else if(user.equals("Guard")){
                    Intent i=new Intent(getApplicationContext(), Security.class);
                    Toast.makeText(getApplicationContext(),"Welcome guard",Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
                else if(user.equals("Admin")){
                    Intent i=new Intent(getApplicationContext(), Admin.class);
                    Toast.makeText(getApplicationContext(),"Welcome Admin",Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Select empty!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String[] st = {"Select", "Student", "Faculity Member","Admin","Security Guard"};
        RegisterSpinnerApdater adapter = new RegisterSpinnerApdater(this, R.layout.spinner1, st);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedOption = (String) adapterView.getItemAtPosition(i);

                if (selectedOption.equals("Student")) {
                    user="Student";
                }
                else if (selectedOption.equals("Faculity Member")) {
                    user="Faculity Member";

                }
                else if (selectedOption.equals("Select"))
                {
                    user="null";
                }
                else if(selectedOption.equals("Security Guard")){
                    user="Guard";
                }
                else if(selectedOption.equals("Admin")){
                    user="Admin";
                }
                else{
                    user="null";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                user="null";
            }
        });


    }
}