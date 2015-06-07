package com.jondroid.jon.mostrarautoresjon;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Jon on 06/06/2015.
 */
public class Autor{

    String nombre;
    String apellido;
    int fechaNac;
    int average_rating;
    String urlPhoto;
    //El bitmap será usado para la clase de detalle en aras de ahorro de tiempo


    public Autor(String nombre, String apellido, int fechaNac, int average_rating, String urlPhoto) {
        this.nombre=nombre;
        this.apellido=apellido;
        this.fechaNac=fechaNac;
        this.average_rating=average_rating;
        this.urlPhoto=urlPhoto;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(int fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(int average_rating) {
        this.average_rating = average_rating;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

}
