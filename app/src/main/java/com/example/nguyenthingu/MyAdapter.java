package com.example.nguyenthingu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<ThiSinh> data;
    LayoutInflater inflater;

    public MyAdapter(Activity activity, ArrayList<ThiSinh> data) {
        this.activity = activity;
        this.data = data;
        this.inflater =(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =view;
        if(v == null){
            v = inflater.inflate(R.layout.item_list, null);
            TextView txtSBD =v.findViewById(R.id.txtSBD);
            txtSBD.setText(data.get(i).getSBD());
            TextView txtHoTen = v.findViewById(R.id.txtHoTen);
            txtHoTen.setText(""+data.get(i).getHoTen());
            TextView txtDiem =v.findViewById(R.id.txtDiemTB);
            txtDiem.setText(""+data.get(i).diemTB());
        }
        return v;
    }
}
