package com.example.pet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pet.user_data.User;
import com.example.pet.my_class.CircleImageView;
import com.example.pet.ui.login.mylogin;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hb.dialog.myDialog.ActionSheetDialog;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import java.io.File;
import java.util.Objects;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.bmob.v3.util.V;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int IMAGE_FROM_CAMERA = 1;
    private static final int IMAGE_FROM_PHOTOS = 2;
    private static final int IMAGE_REQUEST_CUT = 3;


    private Button Bu_like;  //我的收藏
    private Button Bu_money;//我的钱包
    private Button Bu_addpet;//添加宠物
    private Button Bu_doc;//我的帖子
    private Button Bu_dynamic;//我的动态
    private Button Bu_suggest;//建议举报
    private Button Bu_aboutus;//关于我们
    private Button Bu_set;//设置
    private Button Bu_logout;//注销登录

    private File tempFile;
    private String TEMP_IMAGE_PATH;
    private String TEMP_IMAGE_NAME;

    private long firstTime=0; //记录用户首次点击返回键的时间
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Bmob 初始化
        Bmob.initialize(this, "506cc27f3aa60afeabf9fb7b572d042a");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //空间权限获取
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android
                .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        //底部切换
        BottomNavigationView nav_buttom_View = findViewById(R.id.nav_bottom_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController nav_buttom_Controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(nav_buttom_View, nav_buttom_Controller);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //创建文件夹
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File file = new File(Environment.getExternalStorageDirectory() + "/pet/image/");
                        if (!file.exists()) {
                            Log.i("jim", "path1 create:" + file.mkdirs());
                        }
                    }
                    break;
                }else {
                    System.exit(0);
                }
        }
    }

    //  页面刷新
    @Override
    protected void onResume() {
        int id = getIntent().getIntExtra("id", 0);
        BottomNavigationView nav_bottom_view = findViewById(R.id.nav_bottom_view);
        nav_bottom_view.setSelectedItemId(nav_bottom_view.getMenu().getItem(id).getItemId());
        super.onResume();
        change_logined_UI();
    }
    //头像点击
    public void user_headicon_OnClick(View v){
        if(BmobUser.isLogin()){
            image_select();
        }
        else {//跳转登录
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, mylogin.class);
            startActivity(intent);
        }

    }
    //头像点击注销点击
    public void logout_OnClick(View v){
        if(Login_state_verification()){
            BmobUser.logOut();
            toast("注销成功");
            change_logined_UI();
        }
    }
    //添加宠物
    public void add_pet(View v){
        LayoutInflater inflater=LayoutInflater.from( this );
        View add_pet_view = inflater.inflate(R.layout.add_pet_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(add_pet_view);
        Dialog dialog = builder.create();
        EditText patname_input = add_pet_view.findViewById(R.id.patname_input);

        Spinner spinner = add_pet_view.findViewById(R.id.spinner);
        RadioGroup Rg_sex = add_pet_view.findViewById(R.id.Rg_sex);
        RadioGroup Rg_breed = add_pet_view.findViewById(R.id.Rg_breed);

        RadioButton Rb_sex_man = add_pet_view.findViewById(R.id.Rb_sex_man);
        RadioButton breed_dog = add_pet_view.findViewById(R.id.breed_dog);

        Button button = add_pet_view.findViewById(R.id.add);

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String pet_sex = "";
                String pet_name =patname_input.getText().toString();
                String pet_dogorcat= "";
                String pet_breed= spinner.getTransitionName();
                toast(pet_breed);
                if(Rg_sex.getCheckedRadioButtonId() == Rb_sex_man.getId())    pet_sex = "男";
                else   pet_sex = "女";
                if(Rg_breed.getCheckedRadioButtonId() == breed_dog.getId())   pet_dogorcat = "狗";
                else   pet_dogorcat = "猫";



            }
        });



        dialog.show();


    }
    //抽屉弹出逻辑。
    public void DrawerLayout_open(View v){
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.activity_main);
        drawerLayout.openDrawer(Gravity.LEFT);
    }
    //登录按钮和头像名称处UI的显示切换
    public void change_logined_UI(){
        CircleImageView user_headicon = findViewById(R.id.user_headicon);
        TextView user_nickname = findViewById(R.id.user_nickname);
        FloatingActionButton float_user_headicon = findViewById(R.id.float_user_headicon);
        if(BmobUser.isLogin()){
            //System.out.println("已经登录");
            User user = BmobUser.getCurrentUser(User.class);
            user_nickname.setText(user.getUsername());
            user_headicon.setImageResource(R.drawable.ic_userimage);
//            float_user_headicon.setImageResource(R.drawable.ic_userimage);

        }else {
            user_nickname.setText("请登录");
            user_headicon.setImageResource(R.drawable.ic_user);
//            float_user_headicon.setImageResource(R.drawable.ic_user);
        }
    }
    public void toast(String str){
        Toast.makeText(this,str,Toast.LENGTH_LONG).show();
    }
    //验证有无登录,若无登录弹窗显示
    private Boolean Login_state_verification(){
        if(BmobUser.isLogin()){
            return true;
        }else {
            toast("请先登录!!");
            return false;
        }
    }
    //弹窗选择更换头像的来源
    public void image_select(){
        ActionSheetDialog dialog = new ActionSheetDialog(this).builder().setTitle("更换头像")
                .addSheetItem("相册", null, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        User user = BmobUser.getCurrentUser(User.class);
                        TEMP_IMAGE_NAME = user.getUsername() + System.currentTimeMillis() + "temp.jpg";
                        tempFile = new File(Environment.getExternalStorageDirectory()+"/pet/image", TEMP_IMAGE_NAME);
                        photos();
                    }
                }).addSheetItem("拍照", null, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        User user = BmobUser.getCurrentUser(User.class);
                        TEMP_IMAGE_NAME = user.getUsername() + System.currentTimeMillis() + "temp.jpg";
                        tempFile = new File(Environment.getExternalStorageDirectory()+"/pet/image", TEMP_IMAGE_NAME);
                        camera();
                    }
                });
        dialog.show();
    }
    private void camera() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(tempFile));
        }
        startActivityForResult(intent,IMAGE_FROM_CAMERA);
        //调用会返回结果的开启方式，返回成功的话，则把它显示出来
    }
    private void photos() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//这是图片类型
        startActivityForResult(intent, IMAGE_FROM_PHOTOS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case IMAGE_FROM_CAMERA:
                    crop(Uri.fromFile(tempFile)); //调用剪贴图片代码
                    break;
                case IMAGE_FROM_PHOTOS:
                    crop(Objects.requireNonNull(data.getData()));
                    break;
                case IMAGE_REQUEST_CUT:
                    image_upload();
                    break;

                default:
                    break;
            }
        }
    }

    //判断SD卡是否可用
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    // 剪切图片
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
        startActivityForResult(intent, IMAGE_REQUEST_CUT);
    }
    //图片上传
    private void image_upload(){
        TEMP_IMAGE_PATH = Environment.getExternalStorageDirectory()+"/pet/image";
        Log.i(TAG, "image_upload: "+TEMP_IMAGE_PATH+"/"+TEMP_IMAGE_NAME);
        File imagefile = new File(TEMP_IMAGE_PATH+"/"+TEMP_IMAGE_NAME);
        Log.i(TAG, "image_upload: imagefile"+ imagefile.getName());
        Log.i(TAG, "image_upload: imagefile"+ imagefile.getPath());

        BmobFile bmobFile = new BmobFile(imagefile);
        User user = new User();
        user.setHead_icon(bmobFile);

        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
//                if(e==null){
//                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
//                    toast("上传文件成功:" + bmobFile.getFileUrl());
//                }else{
//                    toast("上传文件失败，错误码：" + e.getErrorCode());
//                }

            }
            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }





}
