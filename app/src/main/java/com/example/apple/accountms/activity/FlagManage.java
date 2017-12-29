package com.example.apple.accountms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.FlagDAO;
import com.example.apple.accountms.model.Tb_flag;

public class FlagManage extends AppCompatActivity {
    private EditText txtFlag;
    private Button btnEdit,btnDel;
    private String strid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_manage);
        txtFlag= findViewById(R.id.txtFlagManage); //获取便签文本框
        btnEdit=findViewById(R.id.btnFlagManageEdit); //获取“修改”按钮
        btnDel= findViewById(R.id.btnFlagManageDelete); //获取“删除”按钮
        Intent intent = FlagManage.this.getIntent();
        Bundle bundle = intent.getExtras();
        strid = bundle.getString(Showinfo.FLAG);
        final FlagDAO flagDAO = new FlagDAO(FlagManage.this);
        txtFlag.setText(flagDAO.find(Integer.parseInt(strid)).getFlag());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tb_flag tb_flag = new Tb_flag();
                tb_flag.set_id(Integer.parseInt(strid));
                tb_flag.setFlag(txtFlag.getText().toString());
                flagDAO.update(tb_flag);
                Toast.makeText(FlagManage.this, "〖便签数据〗修改成功！", Toast.LENGTH_SHORT).show();
                reBreak();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagDAO.detele(Integer.parseInt(strid));
                Toast.makeText(FlagManage.this, "〖便签数据〗删除成功！", Toast.LENGTH_SHORT).show();
                reBreak();
            }
        });

    }
    private void reBreak(){
        Intent i = new Intent();
        i.putExtra(Showinfo.TYPE,Showinfo.FLAG_TYPE);
        setResult(Showinfo.RESULT_CODE,i);
        finish();
    }
}
