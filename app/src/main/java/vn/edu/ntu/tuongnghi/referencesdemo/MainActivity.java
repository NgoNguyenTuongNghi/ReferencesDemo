package vn.edu.ntu.tuongnghi.referencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTen, editNgaySinh, editSDT;
    RadioButton rdbNam, rdbNu;
    Button btnLuu, btnDoc, btnXoa;
    
    public static final String sharePrefName = "my_share_preferense";
    public static final String key_ten = "ten";
    public static final String key_ngay_sinh = "ngay_sinh";
    public static final String key_nam = "nam";
    public static final String key_nu = "nu";
    public static final String key_sdt = "so_dien_thoai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        docPref();
    }

    private void addViews() {
        editTen = findViewById(R.id.editName);
        editNgaySinh = findViewById(R.id.editBirthday);
        editSDT = findViewById(R.id.editPhone);
        rdbNam = findViewById(R.id.rdbNam);
        rdbNu = findViewById(R.id.rdbNu);
        btnLuu = findViewById(R.id.buttonLuu);
        btnDoc = findViewById(R.id.buttonDoc);
        btnXoa = findViewById(R.id.buttonXoa);
        btnLuu.setOnClickListener(this);
        btnDoc.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.buttonLuu: luuPref(); break;
            case R.id.buttonDoc: docPref(); break;
            case R.id.buttonXoa: xoaGiaoDien(); break;
        }
    }

    private void docPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(sharePrefName, MODE_PRIVATE);
        if (sharedPreferences != null) {
            String ten = sharedPreferences.getString(key_ten, "chưa có tên");
            String ngaySinh = sharedPreferences.getString(key_ngay_sinh, "chưa có ngày sinh");
            Boolean nam = sharedPreferences.getBoolean(key_nam, true);
            Boolean nu = sharedPreferences.getBoolean(key_nu, false);
            String sdt = sharedPreferences.getString(key_sdt, "chưa có sdt");
            editTen.setText(ten);
            editNgaySinh.setText(ngaySinh);
            editSDT.setText(sdt);
            rdbNam.setChecked(nam);
            rdbNu.setChecked(nu);
        }
    }

    private void luuPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(sharePrefName, MODE_PRIVATE);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key_ten, editTen.getText().toString());
            editor.putString(key_ngay_sinh, editNgaySinh.getText().toString());
            editor.putBoolean(key_nam, rdbNam.isChecked());
            editor.putBoolean(key_nu, rdbNu.isChecked());
            editor.putString(key_sdt, editSDT.getText().toString());
            editor.commit();
        }
    }

    private void xoaGiaoDien() {
        editTen.setText("");
        editNgaySinh.setText("");
        editSDT.setText("");
        rdbNam.setChecked(true);
        rdbNu.setChecked(false);
    }
}