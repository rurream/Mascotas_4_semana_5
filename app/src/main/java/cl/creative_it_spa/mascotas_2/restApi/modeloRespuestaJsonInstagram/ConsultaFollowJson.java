package cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ConsultaFollow;

/**
 * Created by Rodrigo on 15-08-2017.
 */

public class ConsultaFollowJson {

    ArrayList<ConsultaFollow> consultaFollows;

    public ArrayList<ConsultaFollow> getConsultaFollows() {
        return consultaFollows;
    }

    public void setConsultaFollows(ArrayList<ConsultaFollow> consultaFollows) {
        this.consultaFollows = consultaFollows;
    }
}
