package com.example.pet.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pet.R;
import com.example.pet.user_data.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class mylogin extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylogin);
        //this.getSupportActionBar().hide();

        final EditText usernameEditText = findViewById(R.id.usernum);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.change);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Button Register = findViewById(R.id.Register);
        final Button Changepawd = findViewById(R.id.Changepawd);
        //焦点监听
        usernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){}
                else {
                    String usernum_text = usernameEditText.getText().toString();
                    if(usernum_text.length() == 0) usernameEditText.setError("请输入用户名");
                }
            }
        });
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String password_text = passwordEditText.getText().toString();
                    if(!(password_text.length() >= 6 && password_text.length() <= 18) ){
                       passwordEditText.setError("请输入6-18位密码");
                    }
                }
            }
        });
        //登录按钮点击
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("登录按钮点击");
                String usernum_text = usernameEditText.getText().toString();
                String password_text = passwordEditText.getText().toString();
                //点击登录按钮后，如果用户名或密码的输入框内容为空，则报错
                if(usernum_text.length() == 0) usernameEditText.setError("请输入用户名");
                if(password_text.length() == 0) passwordEditText.setError("请输入密码");
                //检查，若无报错信息，则开始登录
                if(usernameEditText.getError() == null && passwordEditText.getError() == null){
                    //登录等待过程显示
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    login(usernum_text,password_text);
                }

            }
        });
        //注册点击
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mylogin.this, com.example.pet.ui.login.Register.class);
                startActivity(intent);
            }
        });
        //找回密码点击
        Changepawd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mylogin.this, com.example.pet.ui.login.Findpassword.class);
                startActivity(intent);
            }
        });

    }

    public void login(String usernum,String password){
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        BmobUser.loginByAccount(usernum , password , new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    //User userinfo = BmobUser.getCurrentUser(User.class);
                    Toast.makeText(mylogin.this,"欢迎！"+user.getUsername(),Toast.LENGTH_LONG).show();

                    finish();


                } else {
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(mylogin.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
