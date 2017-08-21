package cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ListaUsuarios;

/**
 * Created by Rodrigo on 24-07-2017.
 */

public class UsuarioJson {

    ArrayList<ListaUsuarios> listaJsonUsuarios;

    public ArrayList<ListaUsuarios> getListaJsonUsuarios() {
        return listaJsonUsuarios;
    }

    public void setListaJsonUsuarios(ArrayList<ListaUsuarios> listaJsonUsuarios) {
        this.listaJsonUsuarios = listaJsonUsuarios;
    }
}
