package com.example.apple.accountms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.InaccountDAO;
import com.example.apple.accountms.dao.OutaccountDAO;
import com.example.apple.accountms.model.Tb_inaccount;
import com.example.apple.accountms.model.Tb_outaccount;

import java.util.List;

public class Outaccountinfo extends AppCompatActivity {

    public static final String FLAG ="id";//请求码
    private ListView lvinfo;
    private String strType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outaccountinfo);
        lvinfo = findViewById(R.id.lvinaccountinfo);
        showInfo();
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strinfo = String.valueOf(((TextView)view).getText());
                String strid = strinfo.substring(0,strinfo.indexOf("|"));
                Intent intent = new Intent(Outaccountinfo.this,InfoManage.class);
                intent.putExtra(FLAG,new String[]{strid,strType});
                startActivity(intent);
            }
        });
    }

    private void showInfo() {
        String[] strinfos = null;
        ArrayAdapter<String> arrayAdapter = null;
        strType="btnoutinfo";
        OutaccountDAO outaccountDAO = new OutaccountDAO(Outaccountinfo.this);
        List<Tb_outaccount> listinfos = outaccountDAO.getScrollData(0, (int) outaccountDAO.getCount());
        strinfos = new String[listinfos.size()];
        int m = 0;
        for (Tb_outaccount tb_outaccount:listinfos){
            strinfos[m] = tb_outaccount.get_id()+"|"+tb_outaccount.getType()+" "+String.valueOf(tb_outaccount.getMoney())+"元   "+tb_outaccount.getTime();
            m++;
        }
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,strinfos);
        lvinfo.setAdapter(arrayAdapter);
    }
}
