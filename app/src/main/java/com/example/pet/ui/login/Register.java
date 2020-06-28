package com.example.pet.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pet.MainActivity;
import com.example.pet.R;
import com.example.pet.user_data.User;
import com.hb.dialog.myDialog.MyAlertInputDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {
    final User user = new User();


    //错误码
    final static int Er_NUll = 1;
    final static int Er_Fromat = 2;
    final static int Success_Ok = 0;
    final static int navigate = 5;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            if(msg.what == navigate){
                toast("欢迎"+msg.obj);
                Intent intent = new Intent();
                intent.setClass(Register.this, MainActivity.class);
                intent.putExtra("id",0);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                startActivity(intent);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText usernum = findViewById(R.id.usernum);
        final EditText password = findViewById(R.id.password);
        final EditText password_again = findViewById(R.id.password_again);
        final Button register = findViewById(R.id.change);


        usernum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){}
                else {
                    String usernum_text = usernum.getText().toString();
                    switch (UsernumValid(usernum_text)){
                        case Er_NUll:
                            usernum.setError("用户名不可为空");
                            break;
                        case Er_Fromat:
                            usernum.setError("用户名格式错误，只可输入手机号或邮箱");
                            break;
                    }
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){}
                else {
                    String password_text = password.getText().toString();
                    switch (PasswordValid(password_text)){
                        case Er_NUll:
                            password.setError("密码不可为空");
                            break;
                        case Er_Fromat:
                            password.setError("密码格式错误，以英文字符和数字开头，长度在6~18之间，只能包含字符、数字和下划线。");
                            break;
                    }
                }
            }
        });
        password_again.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){}
                else {
                    String password_text = password.getText().toString();
                    String password_again_text = password_again.getText().toString();
                    if(!password_text.equals(password_again_text)){
                        password_again.setError("两次密码输入不一致");
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernum.getError() == null && password.getError() == null && password_again.getError() == null ){
                    String phonenum_text = usernum.getText().toString();
                    String password_text = password.getText().toString();
                    signUp(phonenum_text,password_text);
                }
            }
        });
    }
    private int UsernumValid(String usernum) {
        if (usernum == null) {
            return Er_NUll;
        }
        if (usernum.contains("@")) {
            String regEx_email = "^[A-Za-z0-9]+([_\\.][A-Za-z0-9]+)*@([A-Za-z0-9\\-]+\\.)+[A-Za-z]{2,6}$";
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regEx_email);
            Matcher matcher = pattern.matcher(usernum);
            // 字符串是否与正则表达式相匹配
            if(!matcher.matches()){
                return Er_Fromat;
            }
            //return Patterns.EMAIL_ADDRESS.matcher(usernum).matches();
        }
        else {
            String regEx_phonenum = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regEx_phonenum);
            Matcher matcher = pattern.matcher(usernum);
            // 字符串是否与正则表达式相匹配
            if(!matcher.matches()){
                return Er_Fromat;
            }
        }
        return Success_Ok;
    }
    private int PasswordValid(String password) {


        String regEx_passwd="^[0-9a-zA-Z][\\W\\w]{5,17}$";           // 由数字、26个英文字母或者下划线组成的字符串    6-18个字符
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx_passwd);
        Matcher matcher = pattern.matcher(password);
        // 字符串是否与正则表达式相匹配
        if(!matcher.matches()){
            return Er_Fromat;
        }
        return Success_Ok;
    }
    /**
     * 账号密码注册
     */
    private void signUp(String phonenum_email,String password) {
        final User user = new User();
        //如果用户输入的用户名包含@，设置邮箱
        if (phonenum_email.contains("@")) {
            String regEx_email = "^[A-Za-z0-9]+([_\\.][A-Za-z0-9]+)*@([A-Za-z0-9\\-]+\\.)+[A-Za-z]{2,6}$";
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regEx_email);
            Matcher matcher = pattern.matcher(phonenum_email);
            // 字符串是否与正则表达式相匹配
            if(!matcher.matches()){
                toast("邮箱格式错误");
            }else user.setEmail(phonenum_email);
        }
        //不包含@，设置手机号
        else {
            String regEx_phonenum = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
            // 编译正则表达式
            Pattern pattern = Pattern.compile(regEx_phonenum);
            Matcher matcher = pattern.matcher(phonenum_email);
            // 字符串是否与正则表达式相匹配
            if(!matcher.matches()){
                toast("请输入正确的手机号");
            }else user.setMobilePhoneNumber(phonenum_email);
        }
        user.setPassword(password);
        BmobFile bmobfile =new BmobFile("ic_userimage.jpg","","http://petfile.bmob.cn/2020/05/22/4feb20b44015abdb801609a02d2a0d22.jpg");
        user.setHead_icon(bmobfile);
        //弹窗输入昵称
        final MyAlertInputDialog myAlertInputDialog_nickname = new MyAlertInputDialog(this).builder()
                .setTitle("请输入您的昵称")
                .setEditText("");
        myAlertInputDialog_nickname.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = myAlertInputDialog_nickname.getResult();
                if(nickname.length() == 0){
                    toast("请输入昵称");
                }else {
                    user.setUsername(nickname);
                    myAlertInputDialog_nickname.dismiss();

                    //弹窗输入手机号
                    if(phonenum_email.contains("@")){
                        final MyAlertInputDialog myAlertInputDialog_email = new MyAlertInputDialog(Register.this).builder()
                                .setTitle("请输入您的手机号")
                                .setEditText("");
                        myAlertInputDialog_email.setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String phonenum = myAlertInputDialog_email.getResult();
                                if(phonenum.length() == 0){
                                    toast("请输入手机号");
                                }else {
                                    String regEx_phonenum = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
                                    // 编译正则表达式
                                    Pattern pattern = Pattern.compile(regEx_phonenum);
                                    Matcher matcher = pattern.matcher(phonenum);
                                    // 字符串是否与正则表达式相匹配
                                    if(!matcher.matches()){
                                        toast("手机号格式错误");
                                    }else {
                                        user.setMobilePhoneNumber(phonenum);
                                        myAlertInputDialog_email.dismiss();
                                        user.signUp(new SaveListener<User>() {
                                            @Override
                                            public void done(User user, BmobException e) {
                                                if (e == null) {
                                                    toast("注册成功");
                                                    myAlertInputDialog_email.dismiss();
                                                    Message mes = new Message();
                                                    mes.what = navigate;
                                                    mes.obj = user.getUsername();
                                                    handler.sendMessage(mes);
                                                } else {
                                                    toast("注册失败" + e.getMessage());
                                                }
                                            }
                                        });
                                    }
                                }

                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myAlertInputDialog_email.dismiss();
                            }
                        });
                        myAlertInputDialog_email.show();
                    }
                    //弹窗输入邮箱
                    else {
                        final MyAlertInputDialog myAlertInputDialog_email = new MyAlertInputDialog(Register.this).builder()
                                .setTitle("请输入您的邮箱")
                                .setEditText("");
                        myAlertInputDialog_email.setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String email = myAlertInputDialog_email.getResult();
                                if (email.length() == 0) {
                                    toast("请输入邮箱");
                                } else {
                                    String regEx_email = "^[A-Za-z0-9]+([_\\.][A-Za-z0-9]+)*@([A-Za-z0-9\\-]+\\.)+[A-Za-z]{2,6}$";
                                    // 编译正则表达式
                                    Pattern pattern = Pattern.compile(regEx_email);
                                    Matcher matcher = pattern.matcher(email);
                                    // 字符串是否与正则表达式相匹配
                                    if (!matcher.matches()) {
                                        toast("邮箱格式错误");
                                    } else {
                                        user.setEmail(email);
                                        myAlertInputDialog_email.dismiss();
                                        user.signUp(new SaveListener<User>() {
                                            @Override
                                            public void done(User user, BmobException e) {
                                                if (e == null) {
                                                    toast("注册成功");
                                                    myAlertInputDialog_email.dismiss();
                                                    Message mes = new Message();
                                                    mes.what = navigate;
                                                    mes.obj = user.getUsername();
                                                    handler.sendMessage(mes);
                                                } else {
                                                    toast("注册失败" + e.getMessage());
                                                }
                                            }
                                        });
                                    }
                                }

                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myAlertInputDialog_email.dismiss();
                            }
                        });
                        myAlertInputDialog_email.show();
                    }
                }

            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAlertInputDialog_nickname.dismiss();
            }
        });
        myAlertInputDialog_nickname.show();

    }
    private void toast(String str){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }

}
