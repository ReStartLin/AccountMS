package com.example.apple.accountms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.accountms.R;
import com.example.apple.accountms.dao.OutaccountDAO;
import com.example.apple.accountms.model.Tb_outaccount;

import java.util.List;

public class Outaccountinfo extends AppCompatActivity {
    public static final String OUT_TYPE = "btnoutinfo";
    private ListView lvinfo;
    private String strType;
    private ArrayAdapter<String> arrayAdapter = null;
    private OutaccountDAO outaccountDAO = new OutaccountDAO(Outaccountinfo.this);
    private String[] strinfos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outaccountinfo);
        lvinfo = findViewById(R.id.lvioutaccountinfo);
        showInfo();
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strinfo = String.valueOf(((TextView)view).getText());
                String strid = strinfo.substring(0,strinfo.indexOf("|"));
                Intent intent = new Intent(Outaccountinfo.this,InfoManage.class);
                intent.putExtra(Showinfo.FLAG,new String[]{strid,strType});
                startActivity(intent);
            }
        });
    }

    private void showInfo() {
        strType=OUT_TYPE;
        getData();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,strinfos);
        lvinfo.setAdapter(arrayAdapter);
    }
    private void getData(){
        strinfos = null;
        List<Tb_outaccount> listinfos = outaccountDAO.getScrollData(0, (int) outaccountDAO.getCount());
        strinfos = new String[listinfos.size()];
        int m = 0;
        for (Tb_outaccount tb_outaccount:listinfos){
            strinfos[m] = tb_outaccount.get_id()+"|"+tb_outaccount.getType()+" "+String.valueOf(tb_outaccount.getMoney())+"元   "+tb_outaccount.getTime();
            m++;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (arrayAdapter != null){
            getData();
            arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,strinfos);
            lvinfo.setAdapter(arrayAdapter);
        }
    }
}
