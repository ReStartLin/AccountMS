package com.example.apple.accountms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.InaccountDAO;
import com.example.apple.accountms.dao.OutaccountDAO;
import com.example.apple.accountms.model.Tb_inaccount;
import com.example.apple.accountms.model.Tb_outaccount;

public class InfoManage extends AppCompatActivity {
    protected static final int DATE_DIALOG_ID = 0; //创建日期对话框常量
    private TextView tvtitle, textView; //创建两个TextView对象
    private EditText txtMoney, txtTime, txtHA, txtMark; //创建4个EditText对象
    private Spinner spType; //创建Spinner对象
    private Button btnEdit, btnDel; //创建两个Button对象
    private String[] strInfos; //定义字符串数组
    private String strid, strType; //定义两个字符串变量，分别用来记录信息编号和管理类型
    private int mYear; //年private int mMonth; //月
    private int mDay; //日
    private OutaccountDAO outaccountDAO = new OutaccountDAO(InfoManage.this); //创建OutaccountDAO对象
    private InaccountDAO inaccountDAO = new InaccountDAO(InfoManage.this); //创建InaccountDAO对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_manage);
        tvtitle = (TextView) findViewById(R.id.inouttitle); //获取标题标签对象
        textView = (TextView) findViewById(R.id.tvInOut); //获取“地点/付款方”标签对象
        txtMoney = (EditText) findViewById(R.id.txtInOutMoney); //获取“金额”文本框
        txtTime = (EditText) findViewById(R.id.txtInOutTime); //获取“时间”文本框
        spType = (Spinner) findViewById(R.id.spInOutType); //获取“类别”下拉列表
        txtHA = (EditText) findViewById(R.id.txtInOut); //获取“地点/付款方”文本框
        txtMark = (EditText) findViewById(R.id.txtInOutMark); //获取“备注”文本框
        btnEdit = (Button) findViewById(R.id.btnInOutEdit); //获取“修改”按钮
        btnDel = (Button) findViewById(R.id.btnInOutDelete); //获取“删除”按钮
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        strInfos = bundle.getStringArray(Showinfo.FLAG);
        strid = strInfos[0];
        strType = strInfos[1];
        //支出
        if (strType.equals(Outaccountinfo.OUT_TYPE)) {
            tvtitle.setText("支出管理");
            textView.setText("地 点:");
            //根据编号查找支出信息，并存储到Tb_outaccount对象中
            Tb_outaccount tb_outaccount = outaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));
            txtTime.setText(tb_outaccount.getTime());
            spType.setPrompt(tb_outaccount.getType());
            txtHA.setText(tb_outaccount.getAddress());
            txtMark.setText(tb_outaccount.getMark());
        } else if (strType.equals(Inaccountinfo.IN_TYPE)) {        //收入
            tvtitle.setText("收入管理");
            textView.setText("付款方:");
            Tb_inaccount tb_inaccount = inaccountDAO.find(Integer.parseInt(strid));
            txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));
            txtTime.setText(tb_inaccount.getTime());
            spType.setPrompt(tb_inaccount.getType());
            txtHA.setText(tb_inaccount.getHandler());
            txtMark.setText(tb_inaccount.getMark());
        }
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strType.equals(Outaccountinfo.OUT_TYPE)) //判断类型如果是btnoutinfo
                {
                    Tb_outaccount tb_outaccount = new Tb_outaccount(); //创建Tb_outaccount对象
                    tb_outaccount.set_id(Integer.parseInt(strid)); //设置编号
                    tb_outaccount.setMoney(Double.parseDouble(txtMoney.getText().toString())); //设置金额
                    tb_outaccount.setTime(txtTime.getText().toString()); //设置时间
                    tb_outaccount.setType(spType.getSelectedItem().toString()); //设置类别
                    tb_outaccount.setAddress(txtHA.getText().toString()); //设置地点
                    tb_outaccount.setMark(txtMark.getText().toString()); //设置备注
                    outaccountDAO.update(tb_outaccount); //更新支出信息
                } else if (strType.equals(Inaccountinfo.IN_TYPE)) {
                    Tb_inaccount tb_inaccount = new Tb_inaccount(); //创建Tb_inaccount对象
                    tb_inaccount.set_id(Integer.parseInt(strid)); //设置编号
                    tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText().toString())); //设置金额
                    tb_inaccount.setTime(txtTime.getText().toString()); //设置时间
                    tb_inaccount.setType(spType.getSelectedItem().toString()); //设置类别
                    tb_inaccount.setHandler(txtHA.getText().toString()); //设置付款方
                    tb_inaccount.setMark(txtMark.getText().toString()); //设置备注
                    inaccountDAO.update(tb_inaccount); //更新收入信息
                }
                Toast.makeText(InfoManage.this, "〖数据〗修改成功！", Toast.LENGTH_SHORT).show();
                reBreak(strType);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strType.equals(Outaccountinfo.OUT_TYPE)) {
                    outaccountDAO.detele(Integer.parseInt(strid));
                } else if (strType.equals(Inaccountinfo.IN_TYPE)) {
                    inaccountDAO.detele(Integer.parseInt(strid));
                }
                Toast.makeText(InfoManage.this, "〖数据〗删除成功！", Toast.LENGTH_SHORT).show();
                reBreak(strType);
            }
        });
    }
    private void reBreak(String retype){
        Intent i = new Intent();
        i.putExtra(Showinfo.TYPE,retype);
        setResult(Showinfo.RESULT_CODE,i);
        finish();
    }
}
