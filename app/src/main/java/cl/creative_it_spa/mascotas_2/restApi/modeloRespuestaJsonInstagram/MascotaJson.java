package cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;

/**
 * Created by Rodrigo on 19-07-2017.
 */

public class MascotaJson {
    ArrayList<ListaMascotas> listaJsonMascotas;

    public ArrayList<ListaMascotas> getListaJsonMascotas() {
        return listaJsonMascotas;
    }

    public void setListaJsonMascotas(ArrayList<ListaMascotas> listaJsonMascotas) {
        this.listaJsonMascotas = listaJsonMascotas;
    }
}
