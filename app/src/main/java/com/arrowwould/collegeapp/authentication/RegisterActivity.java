package com.arrowwould.collegeapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName, regEmail, regPassword;
    private Button register;

    private String name, email, pass;

    private FirebaseAuth auth;
    private DatabaseReference reference;
    private DatabaseReference databaseReference;

    private TextView openLog;
    KProgressHUD progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPass);
        register = findViewById(R.id.register);
        openLog = findViewById(R.id.openLog);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        openLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });


    }

    private void openLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            openMainActivity();
        }
    }

    private void openMainActivity() {

        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    private void validateData() {

        name = regName.getText().toString();
        email = regEmail.getText().toString();
        pass = regPassword.getText().toString();
        if (name.isEmpty()) {

            regName.setError("Required Name");
            regName.requestFocus();

        } else if (email.isEmpty()) {
            regEmail.setError("Required Email");
            regEmail.requestFocus();
        } else if (pass.isEmpty()) {
            regPassword.setError("Required Password");
        } else {

            createUser();

        }
    }

    private void createUser() {
        progress = KProgressHUD.create(this);
        progress.setDimAmount(0.5f);
        progress.show();
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            uploadUserData();
                            progress.dismiss();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progress.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                });

    }

    private void uploadUserData() {
        databaseReference = reference.child("users");
        String key = databaseReference.push().getKey();

        HashMap<String, String> user = new HashMap<>();
        user.put("key", key);
        user.put("name", name);
        user.put("email", email);
        user.put("status","no");
//        user.put("email",email);

        databaseReference.child(key).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "user crated", Toast.LENGTH_SHORT).show();
                            openMainActivity();
                        } else {
                            Toast.makeText(RegisterActivity.this, (CharSequence) task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}