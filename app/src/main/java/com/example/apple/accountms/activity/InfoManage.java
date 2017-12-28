package com.example.apple.accountms.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.InaccountDAO;
import com.example.apple.accountms.dao.OutaccountDAO;

public class InfoManage extends AppCompatActivity {
    protected static final int DATE_DIALOG_ID = 0; //创建日期对话框常量
    TextView tvtitle,textView; //创建两个TextView对象
    EditText txtMoney,txtTime,txtHA,txtMark; //创建4个EditText对象
    Spinner spType; //创建Spinner对象
    Button btnEdit,btnDel; //创建两个Button对象
    String[] strInfos; //定义字符串数组
    String strid,strType; //定义两个字符串变量，分别用来记录信息编号和管理类型
    private int mYear; //年private int mMonth; //月
    private int mDay; //日
    OutaccountDAO outaccountDAO=new OutaccountDAO(InfoManage.this); //创建OutaccountDAO对象
    InaccountDAO inaccountDAO=new InaccountDAO(InfoManage.this); //创建InaccountDAO对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_manage);
    }
}
