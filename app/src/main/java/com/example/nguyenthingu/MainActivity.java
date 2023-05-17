package com.example.nguyenthingu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<ThiSinh> arrayList;
    MyAdapter myAdapter;
    int ID;
    Sqlite_DB mysql = new Sqlite_DB(this, "ThiSinh", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        mysql.addContact(new ThiSinh("SBD01", "Nguyễn Thị Ngũ", 10, 9, 9));
        mysql.addContact(new ThiSinh("SBD02", "Ngô Văn Khải", 9, 9, 8));
        mysql.addContact(new ThiSinh("SBD03", "Vũ Trường An", 6, 9, 8.7f));
        mysql.addContact(new ThiSinh("SBD04", "Lê Hải Hà", 9, 7.2f, 8));
        mysql.addContact(new ThiSinh("SBD05", "Lê Đình Đức", 8, 8.5f, 6.9f));
        mysql.addContact(new ThiSinh("SBD06", "Mạc Văn Minh", 6, 9, 6.9f));

        arrayList = mysql.getAllContact();

        ArrayList<String> list = new ArrayList<>();
        for(ThiSinh x : arrayList){
            String[] sx ;
            sx = x.getHoTen().split(" ");
            list.add(sx[sx.length-1]);
        }
        for (int i = 0; i< arrayList.size()-1; i++){
            for (int j = i+1; j< arrayList.size(); j++){
                if(list.get(i).compareTo(list.get(j)) > 0){
                    String s = list.get(i);
                    list.set(i,list.get(j));
                    list.set(j, s);

                    ThiSinh ts = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, ts);
                }
            }
        }

        myAdapter = new MyAdapter(this, arrayList);
        listView.setAdapter(myAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ID = i;
                registerForContextMenu(view);
                return false;
            }
        });

    }

    private void sapxep(){


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:{
                Toast.makeText(MainActivity.this, "Xóa", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.edit:{
                Intent intent = new Intent(MainActivity.this, EditItem.class);
                Bundle bundle = new Bundle();

                ThiSinh x = new ThiSinh(arrayList.get(ID).getSBD(),arrayList.get(ID).getHoTen(),
                        arrayList.get(ID).getToan(), arrayList.get(ID).getLy(), arrayList.get(ID).getHoa());

                bundle.putSerializable("editID", x);
                intent.putExtras(bundle);
                startActivityForResult(intent, 150);
                break;
            }
        }
        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        ThiSinh item = (ThiSinh) bundle.getSerializable("editItem");

        if(requestCode ==150 && resultCode == 250){
            arrayList.set(ID, item);
            mysql.updateContact(arrayList.get(ID).getSBD(),item);
            listView.setAdapter(myAdapter);
            //myAdapter.notifyDataSetChanged();
        }
    }
}