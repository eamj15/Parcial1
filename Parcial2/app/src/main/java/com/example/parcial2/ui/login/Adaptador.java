package com.example.parcial2.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parcial2.R;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private ArrayList<Entidad> listEntidad;
    private Context context;

    public Adaptador(Context context, ArrayList<Entidad> listEntidad) {
        this.context = context;
        this.listEntidad = listEntidad;
    }

    @Override
    public int getCount() {
        return listEntidad.size();
    }

    @Override
    public Object getItem(int position) {
        return listEntidad.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // OBTENER EL OBJETO POR CADA ITEM A MOSTRAR
        final Entidad entidad = (Entidad) getItem(position);
        int resID,pngPos;
        String foto,nota;

        // CREAMOS E INICIALIZAMOS LOS ELEMENTOS DEL ITEM DE LA LISTA
        convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
        final ImageView imgFoto = convertView.findViewById(R.id.imgFoto);
        final TextView tvMateria = convertView.findViewById(R.id.tvMateria);
        final TextView tvSemestre = convertView.findViewById(R.id.tvSemestre);
        final TextView tvNota =  convertView.findViewById(R.id.tvNota);
        final ImageView imgNota = convertView.findViewById(R.id.imgNota);

        // LLENAMOS LOS ELEMENTOS CON LOS VALORES DE CADA ITEM
        pngPos = entidad.getImgFoto().indexOf(".png");
        foto = entidad.getImgFoto().substring(0,pngPos);
        resID = context.getResources().getIdentifier(foto,"drawable",context.getPackageName());
        imgFoto.setImageResource(resID);
        tvMateria.setText(entidad.getMateria());
        tvSemestre.setText(entidad.getSemestre());
        tvNota.setText(entidad.getNota());

        nota = tvNota.getText().toString();
        nota = valImgNota(nota);
        resID = context.getResources().getIdentifier(nota,"drawable",context.getPackageName());
        imgNota.setImageResource(resID);


        SharedPreferences session = context.getSharedPreferences ("session", Context.MODE_PRIVATE);

        if (session.getString("TypeUser","").equals("P")){
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, EditaNota.class);
                    i.putExtra("item", entidad);
                    context.startActivity(i);


                }
            });
        }


        return convertView;
    }

    private String valImgNota(String N){
        if (N.equals("A") || N.equals("B") || N.equals("C")){
            return "aprobada";
        } else if (N.equals("D") || N.equals("F")){
            return "reprobada";
        }

        return "sin_nota";
    }
}
