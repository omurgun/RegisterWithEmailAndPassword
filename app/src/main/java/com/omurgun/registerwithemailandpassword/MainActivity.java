package com.omurgun.registerwithemailandpassword;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(checkCurrentUser()) {
            goLogin();
        }
        else {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentLogin = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                }
            });


            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentRegister = new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(intentRegister);
                    finish();
                }
            });
        }


    }
    private void init(){
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

    }
    private boolean checkCurrentUser() {

        boolean result = false;
        if(firebaseUser!= null)
        {
            result =true;
        }

        return result;
    }
    private void goLogin() {
        Intent intentLogin = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intentLogin);
        finish();
    }

}
