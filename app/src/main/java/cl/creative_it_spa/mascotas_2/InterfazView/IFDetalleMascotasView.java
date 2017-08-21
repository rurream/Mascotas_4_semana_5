package cl.creative_it_spa.mascotas_2.InterfazView;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.Adaptadores.AdaptMascotaSeleccionada;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;

/**
 * Created by Rodrigo on 21-06-2017.
 */

public interface IFDetalleMascotasView {

    public void generarGridLayoutVertical();


    public AdaptMascotaSeleccionada crearAdaptador(ArrayList<ListaMascotas> mascotasdetalle, String id_masc_selected);

    public void inicializarAdaptadorRV(AdaptMascotaSeleccionada adaptador);
}
