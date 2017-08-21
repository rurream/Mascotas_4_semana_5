package cl.creative_it_spa.mascotas_2;


import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ListaUsuarios;
import cl.creative_it_spa.mascotas_2.restApi.Adapter.RestApiAdapter;
import cl.creative_it_spa.mascotas_2.restApi.ConstantesRestApi;
import cl.creative_it_spa.mascotas_2.restApi.EndPointsApi;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.UsuarioJson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigurarCuenta extends AppCompatActivity {

    private Toolbar barra;
    private ArrayList<ListaUsuarios> listaUsuarios;
    private EditText cuenta_usuario;
    private Button bt_buscar, bt_configurar;
    public static  String id_usuario_cuenta="5750131561";
    public static  String nombre_usuario_cuenta="rurream";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_configurar_cuenta);

        barra = (Toolbar) findViewById(R.id.barra_sup);
        setSupportActionBar(barra);
        barra.setTitleTextColor(getResources().getColor(R.color.colorBlanco));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        cuenta_usuario=(EditText) findViewById(R.id.cuenta_usuario);
        bt_buscar=(Button) findViewById(R.id.bt_buscar);
        bt_configurar=(Button) findViewById(R.id.bt_configurar);
        bt_configurar.setEnabled(false);

        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ! cuenta_usuario.getText().toString().matches("")){
                    nombre_usuario_cuenta=cuenta_usuario.getText().toString();
                    obtener_idUsuario();

                } else {
                    bt_configurar.setEnabled(false);
                    Toast.makeText(ConfigurarCuenta.this, "Debe escribir un nombre de usuario.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt_configurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSupportNavigateUp();
            }
        });



        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);



    }


    public void obtener_idUsuario() {
        RestApiAdapter restApiAdapter=new RestApiAdapter();
        Gson gsonUsuario = restApiAdapter.construyeGsonDeserializadorUsuarios();
        EndPointsApi endPointsApi = restApiAdapter.EstablecerConexionRestApiInstagram(gsonUsuario);
        Call<UsuarioJson> usuarioJsonCall=endPointsApi.getDataByUserName(
                ConstantesRestApi.KEY_GET_USER_DATA +
                        ConfigurarCuenta.nombre_usuario_cuenta +
                        ConstantesRestApi.KEY_ampesand +
                        ConstantesRestApi.KEY_ACCESS_TOKEN +
                        ConstantesRestApi.ACCESS_TOKEN );

        usuarioJsonCall.enqueue(new Callback<UsuarioJson>() {
            @Override
            public void onResponse(Call<UsuarioJson> call, Response<UsuarioJson> response) {
                UsuarioJson usuarioJson = response.body();
                listaUsuarios= usuarioJson.getListaJsonUsuarios();

                if (listaUsuarios.size()>0){
                    bt_configurar.setEnabled(true);
                    id_usuario_cuenta=listaUsuarios.get(0).getId_usuario();
                } else {
                    bt_configurar.setEnabled(false);
                    Toast.makeText(ConfigurarCuenta.this, "Nombre de cuenta no válido", Toast.LENGTH_SHORT).show();
                    cuenta_usuario.setText("");
                    nombre_usuario_cuenta="rurream";
                    id_usuario_cuenta="5750131561";
                }
            }

            @Override
            public void onFailure(Call<UsuarioJson> call, Throwable t) {
                //implementar algo por si falla
                Log.e("ERROR EN LA CONEXION", t.toString());
            }
        });
    }
}
