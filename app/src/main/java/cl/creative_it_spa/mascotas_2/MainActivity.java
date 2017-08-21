package cl.creative_it_spa.mascotas_2;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.Adaptadores.PageAdapterMascotas;
import cl.creative_it_spa.mascotas_2.Fragments.FDetalleMascotas;
import cl.creative_it_spa.mascotas_2.Fragments.FListaMascotas;

public class MainActivity extends AppCompatActivity {


    private Toolbar barra1, barra2;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int fragmentSeleccionado=0;
    public static String mascotaLikeada="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.activity_main);

        barra1 = (Toolbar) findViewById(R.id.barra_sup);
        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        setSupportActionBar(barra1);
        barra1.setTitleTextColor(getResources().getColor(R.color.colorBlanco));
        barra2=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(barra1);

        mascotaLikeada="1";

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            try{
                String message = extras.getString("mostrar_detalle");
                if (message.toString().equals("si")){
                    fragmentSeleccionado=1;
                    ConfigurarCuenta.id_usuario_cuenta=extras.getString("id_usuario_cuenta");
                    mascotaLikeada=extras.getString("mascotaLikeada");
                } else {
                    fragmentSeleccionado=0;
                    ConfigurarCuenta.id_usuario_cuenta="5750131561";
                    mascotaLikeada="1";
                }
            } catch (RuntimeException rt){
                Log.d("Error de tipo: ", rt.getMessage());
                fragmentSeleccionado=0;
                ConfigurarCuenta.id_usuario_cuenta="5750131561";
                mascotaLikeada="1";
            }

        }
        extras=null;
        setUpViewPager();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_view, menu);
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.m_about:
                Intent i = new Intent(this, AcernaDe.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Slide slide = new Slide(Gravity.BOTTOM);
                    slide.setDuration(1500);
                    this.getWindow().setExitTransition(slide);
                    this.startActivity(i,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewPager, "").toBundle());
                } else {
                    startActivity(i);
                }
                //startActivity(i);
                break;
            case R.id.m_contacto:
                Intent u = new Intent(this, Contacto.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Fade fade = new Fade();
                    fade.setDuration(1500);
                    this.getWindow().setExitTransition(fade);
                    this.startActivity(u,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewPager, "").toBundle());
                } else {
                    startActivity(u);
                }
                //startActivity(u);
                break;
            case R.id.m_configurar_cuenta:
                Intent v = new Intent(this, ConfigurarCuenta.class);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Slide slide = new Slide(Gravity.TOP);
                    slide.setDuration(1500);
                    this.getWindow().setExitTransition(slide);
                    this.startActivity(v,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewPager, "").toBundle());
                } else {
                    startActivity(v);
                }*/
                startActivity(v);
                break;
            case R.id.mTop_Five:
                Intent llamado=new Intent(getApplicationContext(), Marcador.class);
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    this.getWindow().setExitTransition(explode);
                    this.startActivity(llamado,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this, viewPager, "").toBundle());
                } else {
                    startActivity(llamado);
                }*/
                startActivity(llamado);



                break;
            case R.id.m_recibir_notificaciones:
                Intent notificaciones=new Intent(getApplicationContext(), RecibirNotificaciones.class);
                startActivity(notificaciones);
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments= new ArrayList<>();

        fragments.add(new FListaMascotas());
        fragments.add(new FDetalleMascotas(mascotaLikeada));

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapterMascotas(getSupportFragmentManager(), agregarFragments()));
        viewPager.setCurrentItem(fragmentSeleccionado);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_name);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_negro_lleno);
    }
}
