package com.example.parcial2.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.parcial2.R;

import java.io.File;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText edNombre = findViewById(R.id.ed_nombre);
        final EditText edCedula = findViewById(R.id.ed_cedula);
        final EditText edPwd1 = findViewById(R.id.ed_pwd1);
        final EditText edPwd2 = findViewById(R.id.ed_pwd2);
        final RadioGroup rdGroup = findViewById(R.id.radioGroup);
        final RadioButton rdEst = findViewById(R.id.rd_Est);
        final RadioButton rdProf = findViewById(R.id.rd_Prof);

        final Button btnRegistro = findViewById(R.id.btn_registro);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                btnRegistro.setEnabled(validaForm(edNombre.getText().toString(),
                        edCedula.getText().toString(), edPwd1.getText().toString(),
                        edPwd2.getText().toString(), rdEst.isChecked(), rdProf.isChecked()));
            }
        };

        edNombre.addTextChangedListener(afterTextChangedListener);
        edCedula.addTextChangedListener(afterTextChangedListener);
        edPwd1.addTextChangedListener(afterTextChangedListener);
        edPwd2.addTextChangedListener(afterTextChangedListener);

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                btnRegistro.setEnabled(validaForm(edNombre.getText().toString(),
                        edCedula.getText().toString(), edPwd1.getText().toString(),
                        edPwd2.getText().toString(), rdEst.isChecked(), rdProf.isChecked()));
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type;

                type = (rdEst.isChecked())?"E":"P";

                if (RegUser(edNombre.getText().toString(), edCedula.getText().toString(),
                        edPwd1.getText().toString(), type)){
                    Toast.makeText(getApplicationContext(),"Usuario registrado con exito",Toast.LENGTH_SHORT).show();

                    //limpiamos los campos
                    edNombre.setText("");
                    edCedula.setText("");
                    edPwd1.setText("");
                    edPwd2.setText("");
                    rdEst.setChecked(false);
                    rdProf.setChecked(false);

                } else {
                    Toast.makeText(getApplicationContext(),"Usuario ya existe",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validaForm (String nombre, String cedula, String pwd1, String pwd2, Boolean Est, Boolean Prof){
        boolean activate = false;
        if (nombre.equals("") && cedula.equals("") && pwd1.equals("") && pwd2.equals(""))
        {
            activate = false;
        } else if (pwd1.equals(pwd2) && (Est || Prof) && pwd2.length() > 5){
            activate = true;
        } else {
            activate = false;
        }

        return activate;
    }

    private boolean RegUser (String nombre, String cedula, String pwd, String type){
        File f = new File( "/data/data/"+getApplicationContext().getPackageName()+"/shared_prefs/"+cedula+".xml");
        if (f.exists()){
            return false;
        } else {
            SharedPreferences userData = getSharedPreferences(cedula, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = userData.edit();

            editor.putString("nombre", nombre);
            editor.putString("cedula", cedula);
            editor.putString("pwd", pwd);
            editor.putString("type", type);

            editor.commit();

            return true;
        }
    }
}
