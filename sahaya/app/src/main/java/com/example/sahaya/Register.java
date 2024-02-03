package com.example.sahaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
CardView card1,card2,card3;
AppCompatButton signup;
String user="null",gender="null";
ScrollView scroll;
LinearLayout collegeLayout,rollLayout,addressLayout,occupationLayout;
RadioButton sradio,fradio,aradio,gradio,maleradio,femaleradio,otherradio;
TextView pass_warn;
EditText firstname,lastname,collegename,roll,pass,phone,address,occupation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        card1=findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        signup=findViewById(R.id.signup);

        pass_warn=findViewById(R.id.pass_warn);
        scroll=findViewById(R.id.register_scroll);
        sradio=findViewById(R.id.student_radio);
        aradio=findViewById(R.id.admin_radio);
        fradio=findViewById(R.id.faculity_radio);
        gradio=findViewById(R.id.guard_radio);
        maleradio=findViewById(R.id.male);
        femaleradio=findViewById(R.id.female);
        collegeLayout=findViewById(R.id.collge_parent);
        rollLayout=findViewById(R.id.college_roll_parent);
        addressLayout=findViewById(R.id.address_parent);
        occupationLayout=findViewById(R.id.occupation_register);

        //edit text
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        collegename=findViewById(R.id.collge_name);
        roll=findViewById(R.id.collge_roll);
        pass=findViewById(R.id.pass);
        phone=findViewById(R.id.phoneNo);
        occupation=findViewById(R.id.occupation_edit_register);
otherradio=findViewById(R.id.others);
        maleradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "Male";
            }
        });

        otherradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "Others";
            }
        });
        pass_warn.setVisibility(View.GONE);

        femaleradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = "Female";
            }
        });
        sradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user="Student";
                card1.setVisibility(View.VISIBLE);
                card2.setVisibility(View.VISIBLE);
                card3.setVisibility(View.VISIBLE);
            signup.setVisibility(View.VISIBLE);
                collegeLayout.setVisibility(View.VISIBLE);
                rollLayout.setVisibility(View.VISIBLE);
                occupationLayout.setVisibility(View.GONE);
                addressLayout.setVisibility(View.VISIBLE);

            }

        });


        gradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user="Guard";
                card1.setVisibility(View.VISIBLE);
                card2.setVisibility(View.VISIBLE);
                card3.setVisibility(View.GONE);
                occupationLayout.setVisibility(View.GONE);
                signup.setVisibility(View.VISIBLE);
                collegeLayout.setVisibility(View.VISIBLE);
                rollLayout.setVisibility(View.GONE);
                addressLayout.setVisibility(View.GONE);
            }
        });


        fradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user="Faculity";
                card1.setVisibility(View.VISIBLE);
                card2.setVisibility(View.VISIBLE);
                signup.setVisibility(View.VISIBLE);
                card3.setVisibility(View.VISIBLE);
                occupationLayout.setVisibility(View.VISIBLE);
                collegeLayout.setVisibility(View.VISIBLE);
                rollLayout.setVisibility(View.GONE);
                addressLayout.setVisibility(View.VISIBLE);

            }
        });


        aradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user="Admin";
                card1.setVisibility(View.VISIBLE);
                card2.setVisibility(View.VISIBLE);
                card3.setVisibility(View.VISIBLE);
                occupationLayout.setVisibility(View.GONE);
                signup.setVisibility(View.VISIBLE);

                collegeLayout.setVisibility(View.VISIBLE);
                rollLayout.setVisibility(View.VISIBLE);
                addressLayout.setVisibility(View.VISIBLE);
            }
        });
signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String firstname_=firstname.getText().toString().trim();
        String lastname_=lastname.getText().toString().trim();
        String collegename_=collegename.getText().toString().trim();
        String roll_=roll.getText().toString().trim();
        String pass_=pass.getText().toString().trim();
        String phone_=phone.getText().toString().trim();
        String occupation_=occupation.getText().toString().trim();
        pass_warn.setVisibility(View.GONE);
        if((firstname_+lastname_).equals("")){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"name required!",Toast.LENGTH_SHORT).show();
        }
        else if(collegename_.equals("")){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"college name empty!",Toast.LENGTH_SHORT).show();
        }
        else if(roll_.equals("") && (user.equals("Student")|| user.equals("Admin"))){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"roll empty!",Toast.LENGTH_SHORT).show();
        }
        else if(pass_.equals("")){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"password required!",Toast.LENGTH_SHORT).show();
        }
        else if(!isValid(pass_)){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"invalid password",Toast.LENGTH_SHORT).show();
            pass_warn.setText("** must include atleast one Upper case,one Lowercase and one number character");
            pass_warn.setVisibility(View.VISIBLE);
        }
        else if(phone_.equals("")){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"phone no. empty",Toast.LENGTH_SHORT).show();
        }
        else if(phone_.length()!=10){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"invalid phone no.",Toast.LENGTH_SHORT).show();
        }

        else if(occupation_.equals("") && user.equals("Faculity")){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"Occupation required!",Toast.LENGTH_SHORT).show();
        }
        else if(gender.equals("null")){
            scroll.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(getApplicationContext(),"gender not selected!",Toast.LENGTH_SHORT).show();
        }
        else{



        }

    }
});


    }
    public boolean isValid(String str){
        int u=0,l=0,n=0;
        for(int i=0;i<str.length();i++){
            if(Character.isUpperCase(str.charAt(i))) u=1;
            if(Character.isLowerCase(str.charAt(i))) l=1;
            if(Character.isDigit(str.charAt(i))) n=1;
            if((u+l+n)==3) return true;
        }
        return false;

    }
}