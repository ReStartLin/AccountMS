package com.example.apple.accountms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.apple.accountms.R;
import com.example.apple.accountms.adapter.PictureAdapter;

public class MainActivity extends AppCompatActivity {
    private GridView gvInfo;
    private String[] titles = new String[]{"新增支出","新增收入","我的支出","我的收入","数据管理","系统设置","收支便签","退出"};
    private int[] images = new int[]{R.drawable.addoutaccount,R.drawable.addinaccount,R.drawable.outaccountinfo,
                                                            R.drawable.inaccountinfo,R.drawable.showinfo,R.drawable.sysset,
                                                                               R.drawable.accountflag,R.drawable.exit};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvInfo = findViewById(R.id.gvInfo);
        PictureAdapter adapter = new PictureAdapter(titles,images,MainActivity.this);
        gvInfo.setAdapter(adapter);
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i){
                    case 0:
                        intent = new Intent(MainActivity.this,AddOutaccount.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this,AddInaccount.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this,Outaccountinfo.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this,Inaccountinfo.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this,Showinfo.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this,Sysset.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this,Accountflag.class);
                        startActivity(intent);
                        break;
                    case 7:
                        finish();
                        break;
                }
            }
        });
    }
}
