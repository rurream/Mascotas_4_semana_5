package cl.creative_it_spa.mascotas_2;

import android.os.Build;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Window;

import com.google.firebase.iid.FirebaseInstanceId;

import cl.creative_it_spa.mascotas_2.restApi.Adapter.RestApiAdapter;
import cl.creative_it_spa.mascotas_2.restApi.EndPointsApi;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecibirNotificaciones extends AppCompatActivity {

    private Toolbar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(R.layout.activity_recibir_notificaciones);

        barra = (Toolbar) findViewById(R.id.barra_sup);
        setSupportActionBar(barra);
        barra.setTitleTextColor(getResources().getColor(R.color.colorBlanco));

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


    }

    public void recibirNotificacion(View v){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(refreshedToken);
    }

    public void enviarTokenRegistro(String token){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.EstablecerConexionRestApiFB();
        Call<UsuarioResponse> usuarioResponseCall = endPointsApi.registrarTokenIDTarea(token, ConfigurarCuenta.id_usuario_cuenta);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();

                Log.d ("ID_FireBase", usuarioResponse.getId());
                Log.d ("Token_FireBase", usuarioResponse.getToken());
                Log.d ("id_usuario_instagram", usuarioResponse.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.e("ERROR EN LA CONEXION", t.toString());
            }
        });
    }
}
