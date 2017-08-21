package cl.creative_it_spa.mascotas_2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;

public class AcernaDe extends AppCompatActivity {


    private Toolbar barra;
    //private ArrayList<ListaMascotas> mascotas;
    private TextView tv_acerca_de, tv_nombre_desarrollador, tv_empresa, tv_mail_desarollador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerna_de);

        barra = (Toolbar) findViewById(R.id.barra_sup);
        setSupportActionBar(barra);
        barra.setTitleTextColor(getResources().getColor(R.color.colorBlanco));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        tv_acerca_de=(TextView) findViewById(R.id.tv_acerca_de);
        tv_nombre_desarrollador=(TextView) findViewById(R.id.tv_nombre_desarrollador);
        tv_empresa=(TextView) findViewById(R.id.tv_empresa);
        tv_mail_desarollador=(TextView) findViewById(R.id.tv_mail_desarollador);



    }
}
