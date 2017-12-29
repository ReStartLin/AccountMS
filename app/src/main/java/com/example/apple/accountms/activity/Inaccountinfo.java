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
import com.example.apple.accountms.dao.InaccountDAO;
import com.example.apple.accountms.model.Tb_inaccount;

import java.util.List;

public class Inaccountinfo extends AppCompatActivity {
    public static final String IN_TYPE = "btnininfo";
    private ListView lvinfo;
    private String strType;
    private ArrayAdapter<String> arrayAdapter = null;
    private InaccountDAO inaccountDAO = new InaccountDAO(Inaccountinfo.this);
    private String[] strinfos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inaccountinfo);
        lvinfo = findViewById(R.id.lvinaccountinfo);
        showInfo();
        lvinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strinfo = String.valueOf(((TextView)view).getText());
                String strid = strinfo.substring(0,strinfo.indexOf("|"));
                Intent intent = new Intent(Inaccountinfo.this,InfoManage.class);
                intent.putExtra(Showinfo.FLAG,new String[]{strid,strType});
                startActivity(intent);
            }
        });
    }

    private void showInfo() {

        strType=IN_TYPE;

        getData();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,strinfos);
        lvinfo.setAdapter(arrayAdapter);
    }
    private void getData(){
        strinfos = null;
        List<Tb_inaccount> listinfos = inaccountDAO.getScrollData(0, (int) inaccountDAO.getCount());
        strinfos = new String[listinfos.size()];
        int m = 0;
        for (Tb_inaccount tb_inaccount:listinfos){
            strinfos[m] = tb_inaccount.get_id()+"|"+tb_inaccount.getType()+" "+String.valueOf(tb_inaccount.getMoney())+"元   "+tb_inaccount.getTime();
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
