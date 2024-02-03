package com.example.sahaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Register extends AppCompatActivity {
CardView card1,card2,card3;
AppCompatButton signup;
boolean photodone=false,idcarddone=false;
ImageView imageView,imageView2;
    int REQUEST_IMAGE_CAPTURE=1;
    Uri photo,photoid;
    int REQUEST_CAMERA_PERMISSION=1;
String user="null",gender="null",scancurr="null";
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

        imageView=findViewById(R.id.photo);
        imageView2=findViewById(R.id.idcard);

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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scancurr="photo";
                imageView.setImageResource(R.drawable.image_icon);
                startImageCapture();

//Picasso.get().load(photo).into(imageView2);

            }
        });
imageView2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        scancurr="idcard";
        imageView2.setImageResource(R.drawable.image_icon);
        startImageCapture();

    }
});
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
        else if(!photodone){

            Toast.makeText(getApplicationContext(),"upload your photo",Toast.LENGTH_SHORT).show();

        }
        else if(!idcarddone){
            Toast.makeText(getApplicationContext(),"upload idcard",Toast.LENGTH_SHORT).show();

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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // Set the captured image to the ImageView


            // Process the image for face detection
            processImage(imageBitmap);
        }
    }

    // ... image capture code (same as in the previous example)

    private void processImage(Bitmap imageBitmap) {
        InputImage image = InputImage.fromBitmap(imageBitmap, 0);
        FaceDetectorOptions options =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                        .build();
        FaceDetector detector = FaceDetection.getClient(options);

        Task<List<Face>> result = detector.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<Face>>() {
                    @Override
                    public void onSuccess(List<Face> faces) {
                        // Task completed successfully
                        // Draw rectangles around detected faces
                        if(scancurr.equals("photo")) {
                            imageView.setImageBitmap(drawFaceRectangles(imageBitmap, faces));
                            imageView.setImageBitmap(imageBitmap);
                        }
                        else if(scancurr.equals("idcard")){
                            imageView2.setImageBitmap(drawFaceRectangles(imageBitmap, faces));
                            imageView2.setImageBitmap(imageBitmap);

                        }
                        if (scancurr.equals("photo")) {
                            if (faces.size() > 1) {
                                Toast.makeText(getApplicationContext(), "more than one faces detected, try again", Toast.LENGTH_SHORT).show();
                                imageView.setImageResource(R.drawable.image_icon);
                                photodone=false;
                            } else if (faces.size() == 1) {
                                imageView.setImageBitmap(imageBitmap);
                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                                imageView.setImageBitmap(drawFaceRectangles(imageBitmap, faces));
                                photo = getImageUri(getApplicationContext(), imageBitmap);
                                photodone=true;

                            } else {
                                Toast.makeText(getApplicationContext(), "no face detected, click again", Toast.LENGTH_SHORT).show();
                                imageView.setImageResource(R.drawable.image_icon);
                                photodone=false;
                            }
                        }
                        else if(scancurr.equals("idcard")){
                            if (faces.size() > 1) {
                                Toast.makeText(getApplicationContext(), "invalid idcard , try again", Toast.LENGTH_SHORT).show();
                                imageView2.setImageResource(R.drawable.image_icon);
                                idcarddone=false;
                            } else if (faces.size() == 1) {
                                imageView2.setImageBitmap(imageBitmap);
                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                                imageView2.setImageBitmap(drawFaceRectangles(imageBitmap, faces));
                                photoid = getImageUri(getApplicationContext(), imageBitmap);
                              idcarddone=true;
                            } else {
                                Toast.makeText(getApplicationContext(), "invalid idcard, try again!", Toast.LENGTH_SHORT).show();
                                imageView2.setImageResource(R.drawable.image_icon);
                                idcarddone=false;
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // ... handle the error
                    }
                });
    }

    private Bitmap drawFaceRectangles(Bitmap bitmap, List<Face> faces) {
        // Create a mutable copy of the bitmap
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);


        for (Face face : faces) {
            Rect bounds = face.getBoundingBox();
            canvas.drawRect(bounds, paint);
        }

        return mutableBitmap;
    }

    private void startImageCapture() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            } else {

                Toast.makeText(this, "No camera app available", Toast.LENGTH_SHORT).show();
            }
        } else {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                  startImageCapture();
            } else {

                Toast.makeText(this, "Camera permission required to capture image", Toast.LENGTH_SHORT).show();
            }
        }}

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}