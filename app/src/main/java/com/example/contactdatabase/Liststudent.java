package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Arrays;

public class Liststudent extends AppCompatActivity {
    ScrollView listStudent;
    LinearLayout studentListContainer;
    DatabaseHelper dbHelper;
    Button backHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liststudent);
        mapping();
        dbHelper = new DatabaseHelper(getApplicationContext());
        loadStudentDetails();
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Liststudent.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    protected void loadStudentDetails(){
        String detailsString = dbHelper.getDetails();
        String[] listDetails = detailsString.split(",");
        Log.e("loadStudentDetails: ", Arrays.toString(listDetails));
        for (String detail : listDetails){
            LinearLayout studentLayout = new LinearLayout(this);
            studentLayout.setOrientation(LinearLayout.VERTICAL);
            studentLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 30);
            studentLayout.setLayoutParams(layoutParams);

            TextView studentDetail = new TextView(this);
            studentDetail.setText(detail);
            studentDetail.setTextSize(20);
            if (studentDetail.getText().toString().equals("")) {
                TextView emptyText = new TextView(this);
                emptyText.setText("There are no students on the list");
                emptyText.setTextSize(20);
                emptyText.setGravity(Gravity.CENTER);
                emptyText.setTypeface(emptyText.getTypeface(), Typeface.BOLD);
                studentLayout.addView(emptyText);
            }
            else {
                studentLayout.addView(studentDetail);
                studentLayout.setBackground(getResources().getDrawable(R.drawable.liststudent));
            }
            studentListContainer.addView(studentLayout);
        }
    }
    protected void mapping(){
        listStudent = (ScrollView)findViewById(R.id.showListStudent);
        studentListContainer = (LinearLayout)findViewById(R.id.studentListContainer);
        backHome = (Button)findViewById(R.id.backHome);
    }
}