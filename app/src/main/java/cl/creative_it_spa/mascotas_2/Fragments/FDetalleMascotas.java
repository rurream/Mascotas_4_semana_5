package cl.creative_it_spa.mascotas_2.Fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.Adaptadores.AdaptMascotaSeleccionada;
import cl.creative_it_spa.mascotas_2.InterfazPresentador.IFDetalleMascotasPresentador;
import cl.creative_it_spa.mascotas_2.InterfazView.IFDetalleMascotasView;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
import cl.creative_it_spa.mascotas_2.Presentadores.FDetalleMascotasPresentador;
import cl.creative_it_spa.mascotas_2.R;

/**
 * Created by Rodrigo on 24-07-2017.
 */

public class FDetalleMascotas extends android.support.v4.app.Fragment implements IFDetalleMascotasView {


    private String id_mascota_seleccionada;
    RecyclerView rvMascotaSeleccionada;
    IFDetalleMascotasPresentador presentadorDatos;

    public FDetalleMascotas(String id_mascota_seleccionada) {
        this.id_mascota_seleccionada = id_mascota_seleccionada;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_fdetalle_mascotas, container, false);

        rvMascotaSeleccionada=(RecyclerView) v.findViewById(R.id.rvMascotaSeleccionada);

        if(id_mascota_seleccionada!= "1"){

            presentadorDatos=new FDetalleMascotasPresentador(this, getContext(), id_mascota_seleccionada);
        }
        else{
            FDetalleMascotas fragmento= new FDetalleMascotas("default");
            FragmentTransaction transaccion= ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
            transaccion.replace(R.id.ly_mascota_seleccionada, fragmento);
            transaccion.addToBackStack(null);
            transaccion.commit();
        }
        return v;
    }



    @Override
    public void generarGridLayoutVertical() {
        GridLayoutManager llm=new GridLayoutManager(getActivity(), 3);
        llm.setOrientation(GridLayoutManager.VERTICAL);
        rvMascotaSeleccionada.setLayoutManager(llm);
    }

    @Override
    public AdaptMascotaSeleccionada crearAdaptador(ArrayList<ListaMascotas> mascot_detalle, String id_mascota_seleccionada) {


        AdaptMascotaSeleccionada adaptador=new AdaptMascotaSeleccionada(mascot_detalle, getActivity(), id_mascota_seleccionada);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(AdaptMascotaSeleccionada adaptador) {
        rvMascotaSeleccionada.setAdapter(adaptador);
    }
}
