package com.example.apple.accountms.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.InaccountDAO;
import com.example.apple.accountms.model.Tb_inaccount;

import java.util.Calendar;

public class AddInaccount extends AppCompatActivity {
    protected static final int DATE_DIALOG_ID = 0; //创建日期对话框常量
    private EditText txtInMoney,txtInTime,txtInHandler,txtInMark; //创建4个EditText对象
    private Spinner spInType; //创建Spinner对象
    private Button btnInSaveButton; //创建Button对象“保存”
    private Button btnInCancelButton; //创建Button对象“取消”
    private int mYear; //年
    private int mMonth; //月
    private int mDay; //日
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            updateDisplay();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinaccount);
        txtInMoney=findViewById(R.id.txtInMoney); //获取“金额”文本框
        txtInTime= findViewById(R.id.txtInTime); //获取“时间”文本框
        txtInHandler= findViewById(R.id.txtInHandler); //获取“付款方”文本框
        txtInMark=findViewById(R.id.txtInMark); //获取“备注”文本框
        spInType= findViewById(R.id.spInType); //获取“类别”下拉列表
        btnInSaveButton=findViewById(R.id.btnInSave); //获取“保存”按钮
        btnInCancelButton=findViewById(R.id.btnInCancel); //获取“取消”按钮

        txtInTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();
        btnInSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strInMoney = txtInMoney.getText().toString();
                if (!strInMoney.isEmpty()){
                    InaccountDAO inaccountDAO = new InaccountDAO(AddInaccount.this);
                    Tb_inaccount tb_inaccount = new Tb_inaccount(inaccountDAO.getMaxId()+1,Double.parseDouble(strInMoney),
                            txtInTime.getText().toString(),spInType.getSelectedItem().toString(),
                            txtInHandler.getText().toString(),txtInMark.getText().toString());
                    inaccountDAO.add(tb_inaccount);
                    Toast.makeText(AddInaccount.this, "【新增收入】数据添加成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(AddInaccount.this, "请输入收入金额", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnInCancelButton.setOnClickListener(new View.OnClickListener() { //为“取消”按钮设置监听事件
            @Override
            public void onClick(View arg0) {
//                txtInMoney.setText(""); //设置“金额”文本框为空
//                txtInMoney.setHint("0.00"); //为“金额”文本框设置提示
//                txtInTime.setText(""); //设置“时间”文本框为空
//                txtInTime.setHint("2011-01-01"); //为“时间”文本框设置提示
//                txtInHandler.setText(""); //设置“付款方”文本框为空
//                txtInMark.setText(""); //设置“备注”文本框为空
//                spInType.setSelection(0); //设置“类别”下拉列表默认选择第一项
                finish();
            }
        });
    }

    private void updateDisplay() {
        txtInTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay));

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,mDateSetListener,mYear,mMonth,mDay);
        }
        return null;
    }
}
