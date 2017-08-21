package cl.creative_it_spa.mascotas_2.POJOs;

/**
 * Created by Rodrigo on 24-07-2017.
 */

public class ListaUsuarios {

    private String nombre_usuario;
    private String id_usuario;

    public ListaUsuarios(String nombre_usuario, String id_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.id_usuario = id_usuario;
    }

    public ListaUsuarios() {

    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
}
