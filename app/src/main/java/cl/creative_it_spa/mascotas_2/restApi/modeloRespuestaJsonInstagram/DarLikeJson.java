package cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.LikeMascota;

/**
 * Created by Rodrigo on 25-07-2017.
 */

public class DarLikeJson {

    private ArrayList<LikeMascota> likes_mascota;

    public DarLikeJson(ArrayList<LikeMascota> likes_mascota) {
        this.likes_mascota = likes_mascota;
    }

    public ArrayList<LikeMascota> getLikes_mascota() {
        return likes_mascota;
    }

    public void setLikes_mascota(ArrayList<LikeMascota> likes_mascota) {
        this.likes_mascota = likes_mascota;
    }
}
