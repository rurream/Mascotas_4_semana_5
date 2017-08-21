package cl.creative_it_spa.mascotas_2;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.Adaptadores.PageAdapterMascotas;
import cl.creative_it_spa.mascotas_2.Fragments.FListaMascotas;

public class MascotasUsuarioDioLike extends AppCompatActivity {

    private Toolbar barra;
    private String id_usuario_cuenta="";
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_usuario_dio_like);

        barra = (Toolbar) findViewById(R.id.barra_sup);
        setSupportActionBar(barra);
        barra.setTitleTextColor(getResources().getColor(R.color.colorBlanco));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        viewPager=(ViewPager) findViewById(R.id.viewPager);

        Bundle extras = getIntent().getExtras();
        id_usuario_cuenta=extras.getString("id_usuario_cuenta");

        setUpViewPager();
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments= new ArrayList<>();
        fragments.add(new FListaMascotas());
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapterMascotas(getSupportFragmentManager(), agregarFragments()));
    }
}
