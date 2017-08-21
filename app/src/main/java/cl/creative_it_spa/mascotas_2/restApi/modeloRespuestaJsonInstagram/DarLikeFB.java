package cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram;

/**
 * Created by Rodrigo on 09-08-2017.
 */

public class DarLikeFB {

    private String id_firebase_like;

    public DarLikeFB(String id_firebase_like) {
        this.id_firebase_like = id_firebase_like;
    }

    public DarLikeFB() {
    }

    public String getLike_mascota() {
        return id_firebase_like;
    }

    public void setLike_mascota(String id_firebase_like) {
        this.id_firebase_like = id_firebase_like;
    }
}
