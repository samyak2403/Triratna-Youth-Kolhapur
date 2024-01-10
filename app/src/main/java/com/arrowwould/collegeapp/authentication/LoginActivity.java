package com.arrowwould.collegeapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arrowwould.collegeapp.MainActivity;
import com.arrowwould.collegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kaopiz.kprogresshud.KProgressHUD;

public class LoginActivity extends AppCompatActivity {

    private TextView openReg, openForgetPass;
    private EditText logEmail, logPassword;
    private FirebaseAuth auth;
    private KProgressHUD progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        openReg = findViewById(R.id.openReg);
        logEmail = findViewById(R.id.logEmail);
        logPassword = findViewById(R.id.logPass);
        Button loginBtn = findViewById(R.id.loginBtn);
        openForgetPass = findViewById(R.id.openForgetPass);

        auth = FirebaseAuth.getInstance();

        openReg.setOnClickListener(v -> openRegisterActivity());

        loginBtn.setOnClickListener(v -> validateUserData());

        openForgetPass.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class)));

        // Check if user is already logged in
        if (auth.getCurrentUser() != null) {
            openMainActivity();
        }
    }

    private void validateUserData() {
        String email = logEmail.getText().toString().trim();
        String password = logPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please provide all fields", Toast.LENGTH_SHORT).show();
        } else {
            loginUser(email, password);
        }
    }

    private void loginUser(String email, String password) {
        progress = KProgressHUD.create(this);
        progress.setDimAmount(0.5f);
        progress.show();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        openMainActivity();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    progress.dismiss();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                });
    }

    private void openMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void openRegisterActivity() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }
}
