package com.example.sangeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginactivity extends AppCompatActivity {
    TextView createNewAccount, forgot;
    EditText inputemail, inputpassword;
    Button loginbtn;
    ProgressDialog progressdialog;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        mAuth = FirebaseAuth.getInstance();

        // **Check if a user is already logged in**
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            sendUserToNextActivity();
            return; // Stop executing further code in onCreate
        }

        inputemail = findViewById(R.id.inputemail);
        inputpassword = findViewById(R.id.inputpassword);
        loginbtn = findViewById(R.id.loginbtn);
        createNewAccount = findViewById(R.id.createNewAccount);
        forgot = findViewById(R.id.forgot);
        loginbtn.setOnClickListener(view -> {
            String emailText = inputemail.getText().toString().trim();
            String passwordText = inputpassword.getText().toString().trim();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                            sendUserToNextActivity();
                        } else {
                            Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotemail();
            }
        });

        createNewAccount.setOnClickListener(view -> {
            startActivity(new Intent(this, registeractivity.class));
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(loginactivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void forgotemail() {
        String emailx = inputemail.getText().toString();
        if (!emailx.matches(emailPattern)) {

            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();

        }
        else{
            mAuth.sendPasswordResetEmail(emailx).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(loginactivity.this, "Password reset link sent", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        Toast.makeText(loginactivity.this, "Unregistered email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}