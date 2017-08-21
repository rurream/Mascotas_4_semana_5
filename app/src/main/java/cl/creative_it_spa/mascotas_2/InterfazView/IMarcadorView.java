package cl.creative_it_spa.mascotas_2.InterfazView;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.Adaptadores.AdaptMascotasTop;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;

/**
 * Created by Rodrigo on 21-06-2017.
 */

public interface IMarcadorView {

    public void generarLinearLayoutVertical();


    public AdaptMascotasTop crearAdaptador(ArrayList<ListaMascotas> mascotas);

    public void inicializarAdaptadorRV(AdaptMascotasTop adaptador);
}
