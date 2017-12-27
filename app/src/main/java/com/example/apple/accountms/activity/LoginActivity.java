package com.example.apple.accountms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.PwdDAO;

public class LoginActivity extends AppCompatActivity {
    private EditText txtlogin ;
    private Button btnlogin;
    private Button btnclose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtlogin = findViewById(R.id.txtLogin);
        btnlogin = findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                PwdDAO pwdDAO = new PwdDAO(LoginActivity.this);
                if ((pwdDAO.getCount()==0 | pwdDAO.find().getPassword().isEmpty())
                                                && txtlogin.getText().toString().isEmpty()){
                    //判断是否有密码以及是否输入了密码
                    startActivity(intent);
                }else {
                    //判断输入的密码是否与数据库中的密码一致
                    if (pwdDAO.find().getPassword().equals(txtlogin.getText().toString())){
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "请输入正确的密码！", Toast.LENGTH_SHORT).show();
                    }
                }
                txtlogin.setText("");
            }
        });
        btnclose = findViewById(R.id.btnClose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
