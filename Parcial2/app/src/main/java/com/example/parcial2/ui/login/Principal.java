package com.example.parcial2.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.parcial2.R;

import java.io.File;
import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    private ListView lv;
    private Adaptador adaptador;
    private ArrayList<Entidad> arrayEntidad = new ArrayList<>();

    String Nombre;
    String Cedula;
    String TypeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        cargarNotas();

        Intent intent = getIntent();
        Cedula = intent.getExtras().getString("cedula");

        UserData(Cedula);

        String Pref = (TypeUser.equals("E"))?"Est. ":"Prof. ";

        final TextView txtWelcome = findViewById(R.id.txtWelcome);
        final ImageButton editBtn = findViewById(R.id.editButton);
        lv = findViewById(R.id.listaNotas);

        txtWelcome.setText("Bienvenido/a " + Pref + Nombre);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditUser.class);
                intent.putExtra("cedula",Cedula);
                intent.putExtra("nombre",Nombre);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        arrayEntidad.clear();
        lv.setAdapter(null);
        GetArrayItems();

        SharedPreferences session = getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = session.edit();
        editor.putString("nombre", Nombre);
        editor.putString("cedula", Cedula);
        editor.putString("TypeUser", TypeUser);
        editor.commit();
    }

    private void UserData(String ced){
        SharedPreferences userData = getSharedPreferences(ced, Context.MODE_PRIVATE);

        Nombre = userData.getString("nombre","");
        TypeUser = userData.getString("type","");
    }

    private void GetArrayItems(){
        String foto, materia, semestre, nota;

        for (int i=0;i<10;i++){

            SharedPreferences notas = getSharedPreferences("notas", Context.MODE_PRIVATE);

            materia = notas.getString("m"+String.valueOf(i)+"_nombre","");
            foto = notas.getString("m"+String.valueOf(i)+"_foto","");
            semestre = notas.getString("m"+String.valueOf(i)+"_semestre","");
            nota = notas.getString("m"+String.valueOf(i)+"_nota","");

            arrayEntidad.add(new Entidad(foto, materia, semestre+" Semestre", nota, i));
        }

        adaptador = new Adaptador(this, arrayEntidad);
        lv.setAdapter(adaptador);
    }

    private void cargarNotas(){

        File f = new File( "/data/data/"+getApplicationContext().getPackageName()+"/shared_prefs/notas.xml");
        if (!f.exists()) {
            SharedPreferences notas = getSharedPreferences("notas", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = notas.edit();

            editor.putString("m1_nombre", "Ecologia");
            editor.putString("m1_semestre", "II");
            editor.putString("m1_nota", "C");
            editor.putString("m1_foto","ecologia.png");

            editor.putString("m2_nombre", "Quimica");
            editor.putString("m2_semestre", "II");
            editor.putString("m2_nota", "D");
            editor.putString("m2_foto","quimica.png");

            editor.putString("m3_nombre", "Calculo");
            editor.putString("m3_semestre", "III");
            editor.putString("m3_nota", "B");
            editor.putString("m3_foto","calculo.png");

            editor.putString("m4_nombre", "Finanzas");
            editor.putString("m4_semestre", "IV");
            editor.putString("m4_nota", "F");
            editor.putString("m4_foto","finanzas.png");

            editor.putString("m5_nombre", "Estadistica");
            editor.putString("m5_semestre", "VI");
            editor.putString("m5_nota", "A");
            editor.putString("m5_foto","estadistica.png");

            editor.putString("m6_nombre", "Base de Datos II");
            editor.putString("m6_semestre", "IV");
            editor.putString("m6_nota", "A");
            editor.putString("m6_foto","bdii.png");

            editor.putString("m7_nombre", "Desarrollo de Soft. IV");
            editor.putString("m7_semestre", "VI");
            editor.putString("m7_nota", "B");
            editor.putString("m7_foto","devsodftiv.png");

            editor.putString("m8_nombre", "Ingles Tecnico I");
            editor.putString("m8_semestre", "II");
            editor.putString("m8_nota", "A");
            editor.putString("m8_foto","ingles1.png");

            editor.putString("m9_nombre", "Ingles Tecnico II");
            editor.putString("m9_semestre", "III");
            editor.putString("m9_nota", "B");
            editor.putString("m9_foto","inglesii.png");

            editor.putString("m0_nombre", "Economia");
            editor.putString("m0_semestre", "VII");
            editor.putString("m0_nota", "A");
            editor.putString("m0_foto","economia.png");

            editor.commit();
        }
    }
}
