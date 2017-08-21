package cl.creative_it_spa.mascotas_2.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.Adaptadores.AdaptadorMascotas;
import cl.creative_it_spa.mascotas_2.InterfazView.IFListaMascotasView;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
import cl.creative_it_spa.mascotas_2.Presentadores.FListaMascotasPresentador;
import cl.creative_it_spa.mascotas_2.InterfazPresentador.IFListaMascotasPresentador;
import cl.creative_it_spa.mascotas_2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FListaMascotas extends android.support.v4.app.Fragment implements IFListaMascotasView {


    RecyclerView rvlistaMascotas;
    IFListaMascotasPresentador presentadorDatos;

    public FListaMascotas() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=  inflater.inflate(R.layout.fragment_flista_mascotas, container, false);

        rvlistaMascotas=(RecyclerView) v.findViewById(R.id.rvTotalMascotas);

        presentadorDatos=new FListaMascotasPresentador(this, getContext());

        return v;
    }



    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvlistaMascotas.setLayoutManager(llm);
    }

    @Override
    public AdaptadorMascotas crearAdaptador(ArrayList<ListaMascotas> mascotas) {
        AdaptadorMascotas adaptador =new AdaptadorMascotas(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(AdaptadorMascotas adaptador) {
        rvlistaMascotas.setAdapter(adaptador);
    }
}
