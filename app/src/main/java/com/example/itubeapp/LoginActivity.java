package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                // Handle login logic here
                if (isValidCredentials(enteredUsername, enteredPassword)) {
                    // Login successful, perform corresponding actions
                    navigateToHomeActivity();
                } else {
                    // Login failed
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to SignUpActivity
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // validation logic
        // Return true if validation is successful, false if validation fails
        UserTB userTB = new UserTB(this);
        userTB.open();
        List<User> userList = userTB.selectByAccount(username);
        if (userList.isEmpty()){
            Toast.makeText(this, "User does not exist",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            User user = userList.get(0);
            if (TextUtils.equals(password, user.getPassword())){
                MyApplication.user = user;
                Toast.makeText(this, "Login successful",Toast.LENGTH_SHORT).show();
                return true;
            }else{
                Toast.makeText(this, "Password is wrong",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    private void navigateToHomeActivity() {
        // Perform operations to navigate to the main interface here
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
