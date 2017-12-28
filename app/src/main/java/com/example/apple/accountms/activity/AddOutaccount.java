package com.example.apple.accountms.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.OutaccountDAO;
import com.example.apple.accountms.model.Tb_outaccount;

import java.util.Calendar;

public class AddOutaccount extends AppCompatActivity {
    protected static final int DATE_DIALOG_ID = 0; //创建日期对话框常量
    private EditText txtOutMoney,txtOutTime,txtOutAddress,txtOutMark; //创建4个EditText对象
    private Spinner spOutType; //创建Spinner对象
    private Button btnOutSaveButton; //创建Button对象“保存”
    private Button btnOutCancelButton; //创建Button对象“取消”
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
    protected void onCreate(Bundle savedOutstanceState) {
        super.onCreate(savedOutstanceState);
        setContentView(R.layout.activity_add_outaccount);
        txtOutMoney=findViewById(R.id.txtOutMoney); //获取“金额”文本框
        txtOutTime= findViewById(R.id.txtOutTime); //获取“时间”文本框
        txtOutAddress= findViewById(R.id.txtOutAddress); //获取“付款方”文本框
        txtOutMark=findViewById(R.id.txtOutMark); //获取“备注”文本框
        spOutType= findViewById(R.id.spOutType); //获取“类别”下拉列表
        btnOutSaveButton=findViewById(R.id.btnOutSave); //获取“保存”按钮
        btnOutCancelButton=findViewById(R.id.btnOutCancel); //获取“取消”按钮

        txtOutTime.setOnClickListener(new View.OnClickListener() {
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
        btnOutSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOutMoney = txtOutMoney.getText().toString();
                if (!strOutMoney.isEmpty()){
                    OutaccountDAO outaccountDAO = new OutaccountDAO(AddOutaccount.this);
                    Tb_outaccount tb_outaccount = new Tb_outaccount(outaccountDAO.getMaxId()+1,Double.parseDouble(strOutMoney),
                            txtOutTime.getText().toString(),spOutType.getSelectedItem().toString(),
                            txtOutAddress.getText().toString(),txtOutMark.getText().toString());
                    outaccountDAO.add(tb_outaccount);
                    Toast.makeText(AddOutaccount.this, "【新增支出】数据添加成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(AddOutaccount.this, "请输入支出金额", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnOutCancelButton.setOnClickListener(new View.OnClickListener() { //为“取消”按钮设置监听事件
            @Override
            public void onClick(View arg0) {
//                txtOutMoney.setText(""); //设置“金额”文本框为空
//                txtOutMoney.setHint("0.00"); //为“金额”文本框设置提示
//                txtOutTime.setText(""); //设置“时间”文本框为空
//                txtOutTime.setHint("2011-01-01"); //为“时间”文本框设置提示
//                txtOutAddress.setText(""); //设置“地址”文本框为空
//                txtOutMark.setText(""); //设置“备注”文本框为空
//                spOutType.setSelection(0); //设置“类别”下拉列表默认选择第一项
                finish();
            }
        });
    }

    private void updateDisplay() {
        txtOutTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay));

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
