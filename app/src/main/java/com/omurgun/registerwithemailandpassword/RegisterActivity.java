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


public class RegisterActivity extends AppCompatActivity {

    private EditText txtPassword,txtEmail,txtUsername;
    private Button btnRegister,btnLogin;
    private FirebaseAuth auth;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init(){

        txtPassword = findViewById(R.id.passwordtxt);
        txtEmail = findViewById(R.id.emailtxt);
        txtUsername = findViewById(R.id.usernametxt);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        auth = FirebaseAuth.getInstance();


    }
    private void createNewAccount() {

        final String password = txtPassword.getText().toString();
        final String email = txtEmail.getText().toString();
        username = txtUsername.getText().toString();
        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this,"Username Cannot Be Empty!",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Email Cannot Be Empty!",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Password Cannot Be Empty!",Toast.LENGTH_LONG).show();
        }
        else
        {

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                goLogin();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String message = e.getLocalizedMessage().toString();
                    Toast.makeText(RegisterActivity.this,"Registration failed"+message,Toast.LENGTH_LONG).show();
                }
            });


        }

    }
    private void goLogin() {
        Toast.makeText(RegisterActivity.this,"Your account has been successfully created",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
        intent.putExtra("name",username);
        startActivity(intent);
        finish();
    }
}
