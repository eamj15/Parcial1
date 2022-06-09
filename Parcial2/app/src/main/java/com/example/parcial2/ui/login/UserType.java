package com.example.parcial2.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial2.R;

public class UserType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        final RadioGroup rdGroup = findViewById(R.id.radioGroup2);
        final RadioButton rdSIU = findViewById(R.id.rd_SIU);
        final RadioButton rdEst = findViewById(R.id.rd_estudiante);
        final RadioButton rdProf = findViewById(R.id.rd_profesor);
        final Button btnEntrar = findViewById(R.id.btnEntrar);

        Intent intent = getIntent();
        final String TypeUser = intent.getExtras().getString("type");
        final String Cedula = intent.getExtras().getString("cedula");

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                btnEntrar.setEnabled(true);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (rdSIU.isChecked()){
                    openWb();
                } else {
                    if (rdEst.isChecked() && TypeUser.equals("E")) {
                        welcome(Cedula);
                    } else {
                        if (rdProf.isChecked() && TypeUser.equals("P")) {
                            welcome(Cedula);
                        } else {
                            Toast.makeText(getApplicationContext(), "No tiene permitido este acceso, favor intente con otro.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    private void welcome(String ced) {
        Intent intent = new Intent(this, Principal.class);
        intent.putExtra("cedula",ced);
        startActivity(intent);
    }

    private void openWb() {
        Intent intent = new Intent(this, SIU.class);
        startActivity(intent);
    }
}
