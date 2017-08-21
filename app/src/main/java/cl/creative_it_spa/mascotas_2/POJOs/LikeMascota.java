package cl.creative_it_spa.mascotas_2.POJOs;

import java.util.ArrayList;

/**
 * Created by Rodrigo on 26-07-2017.
 */

public class LikeMascota {

    private int likes_mascota;

    public LikeMascota(int likes_mascota) {
        this.likes_mascota = likes_mascota;
    }

    public LikeMascota() {

    }

    public int getLikes_mascota() {
        return likes_mascota;
    }

    public void setLikes_mascota(int likes_mascota) {
        this.likes_mascota = likes_mascota;
    }
}
