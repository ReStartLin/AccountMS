package com.example.apple.accountms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.FlagDAO;
import com.example.apple.accountms.dao.InaccountDAO;
import com.example.apple.accountms.dao.OutaccountDAO;
import com.example.apple.accountms.model.Tb_flag;
import com.example.apple.accountms.model.Tb_inaccount;
import com.example.apple.accountms.model.Tb_outaccount;

import java.util.List;

public class Showinfo extends AppCompatActivity {
    public static final String FLAG = "id";//请求码
    public static final String TYPE = "type";//请求码
    public static final int CODE = 101;
    public static final String FLAG_TYPE = "btnflaginfo";
    public static final int RESULT_CODE = 100;
    private Button btnflaginfo, btnininfo, btnoutinfo;
    private ListView lvinfo;
    private String strType = null;
    private String[] strInfos = null;
    private ArrayAdapter<String> arrayAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo);
        btnflaginfo = findViewById(R.id.btnflaginfo);
        btnininfo = findViewById(R.id.btnininfo);
        btnoutinfo = findViewById(R.id.btnoutinfo);
        lvinfo = findViewById(R.id.lvinfo);

        btnflaginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfo(R.id.btnflaginfo);
            }
        });
        btnininfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfo(R.id.btnininfo);
            }
        });
        btnoutinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfo(R.id.btnoutinfo);
            }
        });
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() //为ListView添加项单击事件
        {
            //覆写onItemClick()方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strInfo = String.valueOf(((TextView) view).getText()); //记录单击的项信息
                String strid = strInfo.substring(0, strInfo.indexOf('|')); //从项信息中截取编号
                Intent intent = null; //创建Intent对象
                if (strType.equals(Outaccountinfo.OUT_TYPE) || strType.equals(Inaccountinfo.IN_TYPE)) { //判断如果是支出或者收入信息btnoutinfo
                    intent = new Intent(Showinfo.this, InfoManage.class); //使用InfoManage窗口初始化Intent对象
                    intent.putExtra(FLAG, new String[]{strid, strType}); //设置要传递的数据
                } else if (strType == FLAG_TYPE) { //判断如果是便签信息
                    intent = new Intent(Showinfo.this, FlagManage.class); //使用FlagManage窗口初始化Intent对象
                    intent.putExtra(FLAG, strid); //设置要传递的数据
                }
                startActivityForResult(intent, CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE && resultCode == RESULT_CODE) {
            strType = data.getStringExtra(TYPE);
            upData(strType);
            if (arrayAdapter != null) {
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
                lvinfo.setAdapter(arrayAdapter); //为ListView列表设置数据源
            }
        }
    }

    private void showInfo(int intType) {
        switch (intType) {
            case R.id.btnoutinfo:
                strType = Outaccountinfo.OUT_TYPE;

                break;
            case R.id.btnininfo: //如果是btnininfo按钮
                strType = Inaccountinfo.IN_TYPE;
                break;
            case R.id.btnflaginfo: //如果是btnflaginfo按钮
                strType = FLAG_TYPE;

                break;
        }
        upData(strType);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
        lvinfo.setAdapter(arrayAdapter); //为ListView列表设置数据源
    }

    private void upData(String type) {
        strInfos = null;
        switch (type) {
            case Outaccountinfo.OUT_TYPE:
                OutaccountDAO outaccountDAO = new OutaccountDAO(Showinfo.this);
                List<Tb_outaccount> listoutinfos = outaccountDAO.getScrollData(0, (int) outaccountDAO.getCount());
                strInfos = new String[listoutinfos.size()]; //设置字符串数组的长度
                int i = 0; //定义一个开始标识
                for (Tb_outaccount tb_outaccount : listoutinfos) { //遍历List泛型集合
                    strInfos[i] = tb_outaccount.get_id() + "|" + tb_outaccount.getType() + " " + String.valueOf(tb_outaccount.getMoney()) + "元 " + tb_outaccount.getTime();
                    i++; //标识加1
                }
                break;
            case Inaccountinfo.IN_TYPE:
                InaccountDAO inaccountinfo = new InaccountDAO(Showinfo.this); //创建InaccountDAO对象
                List<Tb_inaccount> listinfos = inaccountinfo.getScrollData(0, (int) inaccountinfo.getCount());
                strInfos = new String[listinfos.size()]; //设置字符串数组的长度
                int m = 0; //定义一个开始标识
                for (Tb_inaccount tb_inaccount : listinfos) { //遍历List泛型集合
                    strInfos[m] = tb_inaccount.get_id() + "|" + tb_inaccount.getType() + " " + String.valueOf(tb_inaccount.getMoney()) + "元 " + tb_inaccount.getTime();
                    m++; //标识加1
                }
                break;
            case FLAG_TYPE:
                FlagDAO flaginfo = new FlagDAO(Showinfo.this); //创建FlagDAO对象
                List<Tb_flag> listFlags = flaginfo.getScrollData(0, (int) flaginfo.getCount());
                strInfos = new String[listFlags.size()]; //设置字符串数组的长度
                int n = 0; //定义一个开始标识
                for (Tb_flag tb_flag : listFlags) { //遍历List泛型集合
                    strInfos[n] = tb_flag.get_id() + "|" + tb_flag.getFlag();
                    if (strInfos[n].length() > 15) //判断便签信息的长度是否大于15
                        strInfos[n] = strInfos[n].substring(0, 15) + "……";
                    n++; //标识加1
                }
                break;
        }

    }
}
