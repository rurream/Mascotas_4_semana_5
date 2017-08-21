package cl.creative_it_spa.mascotas_2.InterfazView;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.Adaptadores.AdaptadorMascotas;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;

/**
 * Created by Rodrigo on 21-06-2017.
 * Interfase y View Model
 */

public interface IFListaMascotasView {

    public void generarLinearLayoutVertical();


    public AdaptadorMascotas crearAdaptador(ArrayList<ListaMascotas> mascotas);

    public void inicializarAdaptadorRV(AdaptadorMascotas adaptador);
}
