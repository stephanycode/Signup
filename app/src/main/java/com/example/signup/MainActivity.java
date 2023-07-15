package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Button button;
    EditText name,email,age,password;
    FirebaseAuth firebaseAuth;
    //ssss


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.submit);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        age=findViewById(R.id.age);
        password=findViewById(R.id.password);
        firebaseAuth=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_email=email.getText().toString().trim();
                String user_name=name.getText().toString().trim();
                String user_age=age.getText().toString().trim();
                String user_password=password.getText().toString().trim();

                Users users=new Users(user_name,user_email,user_age);

                firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseDatabase.getInstance().getReference().child("Users").setValue(users);
                            Toast.makeText(MainActivity.this, "User has signed up!!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });



    }
}