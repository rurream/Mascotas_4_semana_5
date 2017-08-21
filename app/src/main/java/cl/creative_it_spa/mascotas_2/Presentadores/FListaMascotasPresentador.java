package cl.creative_it_spa.mascotas_2.Presentadores;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.ConfigurarCuenta;
import cl.creative_it_spa.mascotas_2.InterfazPresentador.IFListaMascotasPresentador;
import cl.creative_it_spa.mascotas_2.InterfazView.IFListaMascotasView;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
import cl.creative_it_spa.mascotas_2.restApi.Adapter.RestApiAdapter;
import cl.creative_it_spa.mascotas_2.restApi.ConstantesRestApi;
import cl.creative_it_spa.mascotas_2.restApi.EndPointsApi;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.MascotaJson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rodrigo on 21-06-2017.
 */

public class FListaMascotasPresentador implements IFListaMascotasPresentador {

    private IFListaMascotasView iFListaMascotasView;
    private Context contexto;
    private ArrayList<ListaMascotas> mascotasBD;

    public FListaMascotasPresentador(IFListaMascotasView ifListaMascotasView, Context contexto) {
        this.iFListaMascotasView = ifListaMascotasView;
        this.contexto = contexto;
        obtenerMediosRecientes2();
    }



    @Override
    public void obtenerMediosRecientes2() {
        RestApiAdapter restApiAdapter2=new RestApiAdapter();
        Gson gsonMediaRecent2 = restApiAdapter2.construyeGsonDeserializadorMediaRecent();
        EndPointsApi endPointsApi2 = restApiAdapter2.EstablecerConexionRestApiInstagram(gsonMediaRecent2);
        Call<MascotaJson> mascotaJsonCall=endPointsApi2.getRecentMediaByUser(
                ConstantesRestApi.KEY_GET_RECENT_MEDIA_USER_1 +
                        ConfigurarCuenta.id_usuario_cuenta +
                        ConstantesRestApi.KEY_GET_RECENT_MEDIA_USER_2 +
                        ConstantesRestApi.KEY_ACCESS_TOKEN +
                        ConstantesRestApi.ACCESS_TOKEN );

        mascotaJsonCall.enqueue(new Callback<MascotaJson>() {
            @Override
            public void onResponse(Call<MascotaJson> call, Response<MascotaJson> response) {
                MascotaJson mascotaJson = response.body();
                mascotasBD= mascotaJson.getListaJsonMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaJson> call, Throwable t) {
                //implementar algo por si falla
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }






    @Override
    public void mostrarMascotasRV() {
        iFListaMascotasView.inicializarAdaptadorRV(iFListaMascotasView.crearAdaptador(mascotasBD));
        iFListaMascotasView.generarLinearLayoutVertical();
    }
}
