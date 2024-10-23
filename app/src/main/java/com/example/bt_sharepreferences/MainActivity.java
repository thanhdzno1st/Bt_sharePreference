package com.example.bt_sharepreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText ed_masv, ed_tensv, ed_lop, ed_khoahoc;
    Button bt_luu;
    SharedPreferences sharedPreferences;
    CheckBox cb_nho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();

        // Load saved data
        sharedPreferences = getSharedPreferences("dataSinhvien", MODE_PRIVATE);
        ed_masv.setText(sharedPreferences.getString("masv", ""));
        ed_tensv.setText(sharedPreferences.getString("tensv", ""));
        ed_lop.setText(sharedPreferences.getString("lop", ""));
        ed_khoahoc.setText(sharedPreferences.getString("khoahoc", ""));
        cb_nho.setChecked(sharedPreferences.getBoolean("checked", false));

        // Save data when button is clicked
        bt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the latest text from EditText fields
                String masv = ed_masv.getText().toString().trim();
                String tensv = ed_tensv.getText().toString().trim();
                String lop = ed_lop.getText().toString().trim();
                String khoahoc = ed_khoahoc.getText().toString().trim();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (cb_nho.isChecked()) {
                    // Save the input data if the checkbox is checked
                    editor.putString("masv", masv);
                    editor.putString("tensv", tensv);
                    editor.putString("lop", lop);
                    editor.putString("khoahoc", khoahoc);
                    editor.putBoolean("checked", true);
                } else {
                    // Remove the saved data if the checkbox is unchecked
                    editor.remove("masv");
                    editor.remove("tensv");
                    editor.remove("lop");
                    editor.remove("khoahoc");
                    editor.remove("checked");
                }
                editor.apply(); // Apply changes
            }
        });
    }

    private void AnhXa() {
        ed_masv = findViewById(R.id.edt_masv);
        ed_tensv = findViewById(R.id.edt_hoten);
        ed_lop = findViewById(R.id.edt_lop);
        ed_khoahoc = findViewById(R.id.edt_khoahoc);
        bt_luu = findViewById(R.id.bt_save);
        cb_nho = findViewById(R.id.cb_remember);
    }
}
