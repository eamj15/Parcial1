package com.example.parcial2.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcial2.R;

public class EditUser extends AppCompatActivity {

    String Nombre;
    String Cedula;
    String FinalPWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Intent intent = getIntent();
        Nombre = intent.getExtras().getString("nombre");
        Cedula = intent.getExtras().getString("cedula");

        SharedPreferences userData = getSharedPreferences(Cedula, Context.MODE_PRIVATE);
        FinalPWD = userData.getString("pwd","");

        final EditText edNombre = findViewById(R.id.edNombre);
        final EditText edPass1 = findViewById(R.id.edPass1);
        final EditText edPass2 = findViewById(R.id.edPass2);
        final Button btnSave = findViewById(R.id.btnSave);
        final Button btnBack = findViewById(R.id.btnCancel);

        edNombre.setText(Nombre);
        edPass1.setText(FinalPWD);
        edPass2.setText(FinalPWD);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edNombre.getText().toString().equals("")
                    && !edPass1.getText().toString().equals("")
                    && !edPass2.getText().toString().equals("")
                    && edPass1.getText().toString().equals(edPass2.getText().toString())
                    && edPass2.getText().toString().length() > 5) {

                    SharedPreferences userData = getSharedPreferences(Cedula, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = userData.edit();

                    editor.putString("nombre", edNombre.getText().toString());
                    editor.putString("pwd", edPass2.getText().toString());

                    editor.commit();

                    Toast.makeText(getApplicationContext(),"Datos actualizados, favor volver a iniciar sesion.",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Favor verificar los datos...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
