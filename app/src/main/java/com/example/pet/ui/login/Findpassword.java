package com.example.pet.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pet.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class Findpassword extends AppCompatActivity {
    private int SEND_FINISH = -1;
    private EditText usernum;
    private EditText new_password;
    private EditText verification_code;
    private Button send_verification_code;
    private Button change;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            
            super.handleMessage(msg);
            if(msg.what == SEND_FINISH){
                send_verification_code.setText("发送");
                send_verification_code.setClickable(true);
            }
            else {
                System.out.println("handle" + msg.what);
                send_verification_code.setText(msg.what + "");
                send_verification_code.setClickable(false);
            }
            
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);

        usernum = findViewById(R.id.usernum);
        new_password = findViewById(R.id.password);
        verification_code = findViewById(R.id.verification_code);

        send_verification_code = findViewById(R.id.send_verification_code);
        change = findViewById(R.id.change);


        usernum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){}
                else {
                    String usernum_text = usernum.getText().toString();
                    String regEx_phonenum = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
                    // 编译正则表达式
                    Pattern pattern = Pattern.compile(regEx_phonenum);
                    Matcher matcher = pattern.matcher(usernum_text);
                    if(!matcher.matches()){
                        usernum.setError("请输入手机号");
                    }
                }
            }
        });
        new_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){}
                else {
                    String new_password_text = new_password.getText().toString();
                    String regEx_passwd="^[0-9a-zA-Z][\\W\\w]{5,17}$";           // 由数字、26个英文字母或者下划线组成的字符串    6-16个字符
                    // 编译正则表达式
                    Pattern pattern = Pattern.compile(regEx_passwd);
                    Matcher matcher = pattern.matcher(new_password_text);
                    // 字符串是否与正则表达式相匹配
                    if(!matcher.matches()){
                        new_password.setError("密码格式错误，以英文字符和数字开头，长度在6~18之间，只能包含字符、数字和下划线。");
                    }
                }
            }
        });
        verification_code.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String verification_code_text = verification_code.getText().toString();
                    String regEx_passwd="^[0-9]{6}$";           // 由数字、26个英文字母或者下划线组成的字符串    6-16个字符
                    // 编译正则表达式
                    Pattern pattern = Pattern.compile(regEx_passwd);
                    Matcher matcher = pattern.matcher(verification_code_text);
                    // 字符串是否与正则表达式相匹配
                    if(!matcher.matches()){
                        verification_code.setError("验证码格式错误");
                    }
                }
            }
        });

        send_verification_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  usernum_text = usernum.getText().toString();
                    if(usernum_text.length() != 0) {
                        BmobSMS.requestSMSCode(usernum_text,"验证码", new QueryListener<Integer>() {
                            @Override
                            public void done(Integer smsId, BmobException e) {
                                if (e == null) {
                                    try {
                                        send_verification_code_UI(60);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                    Toast.makeText(Findpassword.this, "验证码发送成功", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Findpassword.this, "验证码发送失败"+e.getErrorCode()+"-"+e.getMessage(), Toast.LENGTH_LONG).show();
                                    System.out.println("验证码发送失败"+e.getErrorCode()+"-"+e.getMessage());
                                }
                            }
                        });
                    }else {Toast.makeText(Findpassword.this, "请输入手机号", Toast.LENGTH_LONG).show();}
                }

        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernum.getError() == null && new_password.getError() == null && new_password.getError() == null){
                    String  usernum_text = usernum.getText().toString();
                    String  new_password_text = new_password.getText().toString();
                    String  verification_code_text = verification_code.getText().toString();
                    BmobUser.resetPasswordBySMSCode(verification_code_text, new_password_text, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(Findpassword.this,"密码重置成功，请重新登录",Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Findpassword.this,"密码重置失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void send_verification_code_UI(int count) throws InterruptedException {
        final int[] time = {count};
        Timer timer = new Timer();
        send_verification_code = findViewById(R.id.send_verification_code);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time[0]--;
                System.out.println("计时器响应,count值为"+ time[0]);
                Message set_msg = new Message();
                set_msg.what = time[0];
                handler.sendMessage(set_msg);
                if(time[0] == 0) {
                    Message end_msg = new Message();
                    end_msg.what = SEND_FINISH;
                    handler.sendMessage(end_msg);
                    timer.cancel();
                }
            }
        },0,1*1000);
        



    }



}
