package com.example.apple.accountms.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.PwdDAO;
import com.example.apple.accountms.model.Tb_pwd;

public class Sysset extends AppCompatActivity {
    private EditText txtpwd; //创建EditText对象
    private Button btnSet,btnsetCancel; //创建两个Button对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sysset);
        txtpwd=findViewById(R.id.txtPwd); //获取密码文本框
        btnSet= findViewById(R.id.btnSet); //获取“设置”按钮
        btnsetCancel=findViewById(R.id.btnsetCancel); //获取“取消”按钮
        btnSet.setOnClickListener(new View.OnClickListener() { //为“设置”按钮添加监听事件
            @Override
            public void onClick(View arg0) {
                PwdDAO pwdDAO=new PwdDAO(Sysset.this); //创建PwdDAO对象
                Tb_pwd tb_pwd=new Tb_pwd(txtpwd.getText().toString()); //根据输入的密码创建Tb_pwd对象
                if(pwdDAO.getCount()==0){ //判断数据库中是否已经设置了密码
                    pwdDAO.add(tb_pwd); //添加用户密码
                }else {
                    pwdDAO.update(tb_pwd); //修改用户密码
                }
                Toast.makeText(Sysset.this, "〖密码〗设置成功！", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
        btnsetCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
