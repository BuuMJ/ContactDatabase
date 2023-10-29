package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtBirthday, edtEmail;
    Button btnView, btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtName.getText().toString().equals("")){
                    edtName.setError("Please fill in the student's name");
                }
                else if(edtBirthday.getText().toString().equals("")){
                    edtBirthday.setError("Please fill in the student's birthday");
                }
                else if(edtEmail.getText().toString().equals("")){
                    edtEmail.setError("Please fill in the student's email");
                }
                else{
                    saveStudent();
                    edtBirthday.setText("");
                    edtEmail.setText("");
                    edtName.setText("");
                }
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Liststudent.class);
                startActivity(intent);
            }
        });

    }
    private void saveStudent(){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        String name = edtName.getText().toString();
        String birthday = edtBirthday.getText().toString();
        String email = edtEmail.getText().toString();
        long studentId = dbHelper.inserDetails(name,birthday,email);
        Toast.makeText(this, name + " has been added to the list", Toast.LENGTH_SHORT).show();
    }
    protected void mapping(){
        edtName = (EditText)findViewById(R.id.edtName);
        edtBirthday = (EditText)findViewById(R.id.edtBirthday);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnView = (Button)findViewById(R.id.btnView);
    }
}