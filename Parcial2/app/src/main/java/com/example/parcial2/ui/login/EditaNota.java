package com.example.parcial2.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial2.R;

public class EditaNota extends AppCompatActivity {
    private Entidad item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_nota);

        item = (Entidad) getIntent().getSerializableExtra("item");

        final TextView txtMateria = findViewById(R.id.txtNombreMat);
        final Button btnBack = findViewById(R.id.btnBack);
        final Button btnGuarda = findViewById(R.id.btnGuarda);
        final RadioButton optA = findViewById(R.id.optionA);
        final RadioButton optB = findViewById(R.id.optionB);
        final RadioButton optC = findViewById(R.id.optionC);
        final RadioButton optD = findViewById(R.id.optionD);
        final RadioButton optF = findViewById(R.id.optionF);
        final RadioButton optSN = findViewById(R.id.optionSN);

        txtMateria.setText(item.getMateria());

        if (item.getNota().equals("A")) {
            optA.setChecked(true);
        }
        if (item.getNota().equals("B")) {
            optB.setChecked(true);
        }
        if (item.getNota().equals("C")) {
            optC.setChecked(true);
        }
        if (item.getNota().equals("D")) {
            optD.setChecked(true);
        }
        if (item.getNota().equals("F")) {
            optF.setChecked(true);
        }
        if (item.getNota().equals("")) {
            optSN.setChecked(true);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences notas = getSharedPreferences("notas", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = notas.edit();

                if (optA.isChecked()){
                    editor.putString("m"+String.valueOf(item.getIdentify())+"_nota", "A");
                }
                if (optB.isChecked()){
                    editor.putString("m"+String.valueOf(item.getIdentify())+"_nota", "B");
                }
                if (optC.isChecked()){
                    editor.putString("m"+String.valueOf(item.getIdentify())+"_nota", "C");
                }
                if (optD.isChecked()){
                    editor.putString("m"+String.valueOf(item.getIdentify())+"_nota", "D");
                }
                if (optF.isChecked()){
                    editor.putString("m"+String.valueOf(item.getIdentify())+"_nota", "F");
                }
                if (optSN.isChecked()){
                    editor.putString("m"+String.valueOf(item.getIdentify())+"_nota", "");
                }

                editor.commit();

                Toast.makeText(getApplicationContext(),"Nota actualizada correctamente!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
