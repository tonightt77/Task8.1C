package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    private EditText fullname;
    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        createAccount = findViewById(R.id.create_account);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredFullName = fullname.getText().toString();
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                String enteredConfirmPassword = confirmPassword.getText().toString();
                // Handle account creation logic here
                if (isValidInput(enteredFullName, enteredUsername, enteredPassword, enteredConfirmPassword)) {
                    // Input is valid, perform account creation operation
                    createNewAccount(enteredFullName, enteredUsername, enteredPassword);
                } else {
                    // Input is invalid, display an error message
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidInput(String fullName, String username, String password, String confirmPassword) {
        // This method will handle the input validation, checking if fields are not empty.
        if(fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            return false;
        }
        // Check if password and confirmPassword match
        if(!password.equals(confirmPassword)) {
            return false;
        }
        // Check if password meets the requirements (you can modify the requirements as per your needs)
        if(password.length() < 8) {
            return false;
        }
        return true;
    }

    private void createNewAccount(String fullName, String username, String password) {
        // Perform account creation operation here, save user information to the database.
        User user = new User();
        user.setFullname(fullName);
        user.setPassword(password);
        user.setUsername(username);
        UserTB userTB = new UserTB(this);
        userTB.open();
        List<User> userList = userTB.selectByAccount(username);
        if (userList.isEmpty()){
            userTB.insert(user);
            Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
            // After successful creation, redirect user to the LoginActivity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "User already existed",Toast.LENGTH_SHORT).show();
        }

    }
}
