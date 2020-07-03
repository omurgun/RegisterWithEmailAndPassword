package com.omurgun.registerwithemailandpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    private Button btnLogin,btnRegister;
    private EditText txtEmail,txtPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtEmail = findViewById(R.id.emailtxt);
        txtPassword = findViewById(R.id.passwordtxt);
        firebaseAuth = FirebaseAuth.getInstance();


    }
    private void loginUser() {

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Email Cannot Be Empty!", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Password Cannot Be Empty!", Toast.LENGTH_LONG).show();
        }
        else
        {
            btnLogin.setEnabled(false);
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    goLogin();
                }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_LONG).show();
                    btnLogin.setEnabled(true);
                }
                });
        }
    }
    private void goLogin() {
        Intent intentLogin = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intentLogin);
        finish();
    }
}
