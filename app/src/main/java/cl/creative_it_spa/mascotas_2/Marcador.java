package cl.creative_it_spa.mascotas_2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.Adaptadores.AdaptMascotasTop;
import cl.creative_it_spa.mascotas_2.InterfazPresentador.IFListaMascotasPresentador;
import cl.creative_it_spa.mascotas_2.InterfazPresentador.IMarcadorPresentador;
import cl.creative_it_spa.mascotas_2.InterfazView.IMarcadorView;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
import cl.creative_it_spa.mascotas_2.POJOs.MascotasTopFive;
import cl.creative_it_spa.mascotas_2.Presentadores.MarcadorPresentador;

public class Marcador extends AppCompatActivity implements IMarcadorView {

    private Toolbar barra;
    private RecyclerView rvlistaMascotasTop;
    IMarcadorPresentador presentadorDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcador);

        barra = (Toolbar) findViewById(R.id.barra_sup);
        setSupportActionBar(barra);
        barra.setTitleTextColor(getResources().getColor(R.color.colorBlanco));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        rvlistaMascotasTop=(RecyclerView) findViewById(R.id.rvTop_5_Mascotas);


        presentadorDatos=new MarcadorPresentador(this, this);
    }



    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvlistaMascotasTop.setLayoutManager(llm);
    }


    @Override
    public AdaptMascotasTop crearAdaptador(ArrayList<ListaMascotas> mascotasTop5) {

        AdaptMascotasTop adaptadorTop =new AdaptMascotasTop(mascotasTop5, this);
        return adaptadorTop;
    }


    @Override
    public void inicializarAdaptadorRV(AdaptMascotasTop adaptadorTop) {
        rvlistaMascotasTop.setAdapter(adaptadorTop);
    }
}
