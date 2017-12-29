package com.example.apple.accountms.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.FlagDAO;
import com.example.apple.accountms.model.Tb_flag;

public class Accountflag extends AppCompatActivity {
    private EditText txtFlag;
    private Button btnflagSaveButton;
    private Button btnflagCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountflag);
        txtFlag = findViewById(R.id.txtFlag);
        btnflagCancelButton = findViewById(R.id.btnflagCancel);
        btnflagSaveButton = findViewById(R.id.btnflagSave);
        btnflagSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strflag = txtFlag.getText().toString();
                if (!strflag.isEmpty()){
                    FlagDAO flagDAO = new FlagDAO(Accountflag.this);
                    Tb_flag tb_flag = new Tb_flag(flagDAO.getMaxId()+1,strflag);
                    flagDAO.add(tb_flag);
                    Toast.makeText(Accountflag.this, "〖新增便签〗数据添加成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(Accountflag.this, "请输入便签！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnflagCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
