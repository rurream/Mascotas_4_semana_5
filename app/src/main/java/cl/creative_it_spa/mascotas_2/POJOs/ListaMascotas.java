package cl.creative_it_spa.mascotas_2.POJOs;

import java.util.ArrayList;

/**
 * Created by Rodrigo on 13-06-2017.
 */

public class ListaMascotas {

    private String nombre_mascota;
    private int puntaje_mascota;
    private String id;
    private String foto_mascota;
    private String fecha_creacion;
    private String fullName;

    public ListaMascotas(String nombre_mascota, int puntaje_mascota, String foto_mascota,
                         String id, String fecha_creacion, String fullName) {
        this.nombre_mascota = nombre_mascota;
        this.puntaje_mascota = puntaje_mascota;
        this.foto_mascota = foto_mascota;
        this.id = id;
        this.fecha_creacion=fecha_creacion;
        this.fullName=fullName;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public ListaMascotas() {

    }

    public String getNombre_mascota() {
        return nombre_mascota;
    }

    public void setNombre_mascota(String nombre_mascota) {
        this.nombre_mascota = nombre_mascota;
    }

    public int getPuntaje_mascota() {
        return puntaje_mascota;
    }

    public void setPuntaje_mascota(int puntaje_mascota) {
        this.puntaje_mascota = puntaje_mascota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto_mascota() {
        return foto_mascota;
    }

    public void setFoto_mascota(String foto_mascota) {
        this.foto_mascota = foto_mascota;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

