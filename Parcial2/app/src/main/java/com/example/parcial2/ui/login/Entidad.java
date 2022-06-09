package com.example.parcial2.ui.login;

import java.io.Serializable;

public class Entidad implements Serializable {

    private String imgFoto;
    private String materia;
    private String semestre;
    private String nota;
    private int identify;

    public Entidad(String imgFoto, String materia, String semestre, String nota, int identify) {
        this.imgFoto = imgFoto;
        this.materia = materia;
        this.semestre = semestre;
        this.nota = nota;
        this.identify = identify;
    }

    public String getImgFoto() {
        return imgFoto;
    }

    public String getMateria() {
        return materia;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getNota() {
        return nota;
    }

    public int getIdentify() {
        return identify;
    }
}