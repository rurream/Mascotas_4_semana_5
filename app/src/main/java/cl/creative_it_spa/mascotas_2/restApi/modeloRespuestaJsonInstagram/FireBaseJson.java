package cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.RegistroFireBase;

/**
 * Created by Rodrigo on 08-08-2017.
 */

public class FireBaseJson {

    private ArrayList<RegistroFireBase> respuestaFB;

    public FireBaseJson(ArrayList<RegistroFireBase> respuestaFB) {
        this.respuestaFB = respuestaFB;
    }

    public ArrayList<RegistroFireBase> getRespuestaFB() {
        return respuestaFB;
    }

    public void setRespuestaFB(ArrayList<RegistroFireBase> respuestaFB) {
        this.respuestaFB = respuestaFB;
    }
}
