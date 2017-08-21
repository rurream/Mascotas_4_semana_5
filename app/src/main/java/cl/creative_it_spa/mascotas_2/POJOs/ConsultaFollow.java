package cl.creative_it_spa.mascotas_2.POJOs;

/**
 * Created by Rodrigo on 15-08-2017.
 */

public class ConsultaFollow {

    private String estado_follow;

    public ConsultaFollow(String estado_follow) {
        this.estado_follow = estado_follow;
    }

    public ConsultaFollow() {
    }

    public String getEstado_follow() {
        return estado_follow;
    }

    public void setEstado_follow(String estado_follow) {
        this.estado_follow = estado_follow;
    }
}
