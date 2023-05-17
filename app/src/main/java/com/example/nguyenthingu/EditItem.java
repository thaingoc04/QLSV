package com.example.nguyenthingu;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditItem extends AppCompatActivity {

    TextView editSBD, editTen, editToan, editLy, editHoa;
    Button btnSua, btnThoat;
    ThiSinh x = new ThiSinh();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editSBD = findViewById(R.id.editSBD);
        editTen = findViewById(R.id.editTen);
        editToan = findViewById(R.id.editToan);
        editLy = findViewById(R.id.editLy);
        editHoa = findViewById(R.id.editHoa);

        btnSua = findViewById(R.id.btnEdit);
        btnThoat = findViewById(R.id.btnThoat);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            x = (ThiSinh) bundle.getSerializable("editID");
            editSBD.setText(x.getSBD());
            editTen.setText(x.getHoTen());
            editToan.setText(String.valueOf(x.getToan()));
            editLy.setText(String.valueOf(x.getLy()));
            editHoa.setText(String.valueOf(x.getHoa()));

        }

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditItem.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String validate = isValidate(editSBD.getText().toString(), editTen.getText().toString(),
                        editToan.getText().toString(), editLy.getText().toString() , editHoa.getText().toString());
                if(validate.equals("")){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    ThiSinh item = new ThiSinh(editSBD.getText().toString(), editTen.getText().toString(),Float.parseFloat(editToan.getText().toString()),
                            Float.parseFloat( editLy.getText().toString()),Float.parseFloat(editHoa.getText().toString()));
                    bundle.putSerializable("editItem", item);
                    intent.putExtras(bundle);
                    setResult(250, intent);
                    finish();
                }
                else {
                    Toast.makeText(EditItem.this, validate, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private String isValidate(String sbd, String ten,String toan,String ly, String hoa){
        if(sbd.equals("")) return "Số báo danh không được để trống";
        if(ten.equals("")) return "Tên không được để trống";
        if(toan.equals("")) return "Điểm toán không được để trống";
        if(ly.equals("")) return  "Điểm lý không được để trống";
        if(hoa.equals("")) return "Điểm hóa không được để trống";
        return "";
    }
}