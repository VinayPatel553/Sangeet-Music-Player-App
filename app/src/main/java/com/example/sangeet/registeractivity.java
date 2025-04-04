package com.example.sangeet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class registeractivity extends AppCompatActivity {
    private TextView alreadyHaveAccount;
    private EditText inputemail, inputpassword, inputconfirmpassword;
    private Button loginbtn;
    private ProgressDialog progressdialog;
    private FirebaseAuth mAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);

        mAuth = FirebaseAuth.getInstance();

        inputemail = findViewById(R.id.inputemail);
        inputpassword = findViewById(R.id.inputpassword);
        inputconfirmpassword = findViewById(R.id.inputconfirmpassword);
        loginbtn = findViewById(R.id.loginbtn);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        progressdialog = new ProgressDialog(this);

        loginbtn.setOnClickListener(view -> performAuth());

        alreadyHaveAccount.setOnClickListener(view -> {
            Intent intent = new Intent(registeractivity.this, loginactivity.class);
            startActivity(intent);
        });
    }

    private void performAuth() {
        String email = inputemail.getText().toString().trim();
        String pass = inputpassword.getText().toString().trim();
        String cpass = inputconfirmpassword.getText().toString().trim();

        if (!email.matches(emailPattern)) {
            inputemail.setError("Enter a valid Email");
        } else if (pass.isEmpty() || pass.length() < 6) {
            inputpassword.setError("Password must be at least 6 characters");
        } else if (!pass.equals(cpass)) {
            inputconfirmpassword.setError("Passwords do not match");
        } else {
            progressdialog.setMessage("Registering...");
            progressdialog.setTitle("Registration");
            progressdialog.setCanceledOnTouchOutside(false);
            progressdialog.show();

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {
                        progressdialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(registeractivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            sendUserToNextActivity();
                        } else {
                            Toast.makeText(registeractivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(registeractivity.this, loginactivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}