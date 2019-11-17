package com.example.finalexam07600613;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalexam07600613.Database.UserDatabaseEntity;
import com.example.finalexam07600613.Database.UserDatabaseRepository;
import com.example.finalexam07600613.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //--------Database Handler----------------------------
        final List<User> userList = new ArrayList<>();
        UserDatabaseRepository repo = new UserDatabaseRepository(this);
        repo.getUser(new UserDatabaseRepository.GetCallback() {
            @Override
            public void onSuccess(List<UserDatabaseEntity> entityList) {
                System.out.println("Now your on login page");
                for(UserDatabaseEntity entity :entityList){
                    userList.add(new User(entity.username,entity.fullname,entity.password));
                    System.out.println("Have a user fullname : "+entity.fullname);
                }

            }
        });
        //-------------------------------------------------------

        Button loginButton = findViewById(R.id.login_button);
        Button regisButton = findViewById(R.id.register_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("press login");
                EditText _username = findViewById(R.id.username_edit_text);
                EditText _password = findViewById(R.id.password_edit_text);
                String name = _username.getText().toString();
                String password = _password.getText().toString();
                if((name.equals("")||password.equals(""))){
                    System.out.println("login empty fill");

                    Toast.makeText(LoginActivity.this,"All fields are required",Toast.LENGTH_LONG).show();
                }
                else{
                    Boolean check = false;
                    for(User u : userList){
                        if(name.equals(u.username)&&password.equals(u.password)){
                            Toast.makeText(LoginActivity.this,"Welcome "+u.fullname,Toast.LENGTH_LONG).show();
                            check = true;
                        }
                    }
                    if(!check){
                        Toast.makeText(LoginActivity.this,"Invalid username or password",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                LoginActivity.this.finish();
            }
        });
    }
}
