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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button regisButton = findViewById(R.id.register_button);
        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _username = findViewById(R.id.username_edit_text);
                EditText _fullname = findViewById(R.id.full_name_edit_text);
                EditText _password = findViewById(R.id.password_edit_text);
                final String username = _username.getText().toString();
                final String fullname = _fullname.getText().toString();
                final String password = _password.getText().toString();
                if(username.equals("")||fullname.equals("")||password.equals("")){
                    System.out.println("login empty fill");

                    Toast.makeText(RegisterActivity.this,"All fields are required",Toast.LENGTH_LONG).show();
                }
                else{
                    final UserDatabaseRepository repo = new UserDatabaseRepository(RegisterActivity.this);
                    final List<User> userlist = new ArrayList<>();
                    repo.getUser(new UserDatabaseRepository.GetCallback() {
                        @Override
                        public void onSuccess(List<UserDatabaseEntity> entityList) {
                            Boolean check = false;
                            System.out.println("Now your on register page");
                            for(UserDatabaseEntity entity :entityList){
                                if(entity.username.equals(username)){
                                    check = true;
                                    Toast.makeText(RegisterActivity.this,"username already used",Toast.LENGTH_LONG).show();
                                }
                            }
                            if(!check){
                                repo.insertAppBoard(new UserDatabaseEntity(username, fullname, password), new UserDatabaseRepository.InsertCallback() {
                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(RegisterActivity.this,"Register successfully",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                        RegisterActivity.this.finish();
                                    }
                                });
                            }
                        }
                    });

                }
            }
        });
    }
}
