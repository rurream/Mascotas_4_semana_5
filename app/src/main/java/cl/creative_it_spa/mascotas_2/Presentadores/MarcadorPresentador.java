package cl.creative_it_spa.mascotas_2.Presentadores;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.ConfigurarCuenta;
import cl.creative_it_spa.mascotas_2.InterfazPresentador.IMarcadorPresentador;
import cl.creative_it_spa.mascotas_2.InterfazView.IMarcadorView;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
import cl.creative_it_spa.mascotas_2.POJOs.MascotasTopFive;
import cl.creative_it_spa.mascotas_2.POJOs.OrdenarMascotasTiempo;
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

public class MarcadorPresentador implements IMarcadorPresentador{

    private IMarcadorView iFListaMascotasView;
    private Context contexto;
    private ArrayList<ListaMascotas> mascotasRecientesDeserdenadas, mascotasRecientesOrdenadas, mascotasTop5;

    public MarcadorPresentador(IMarcadorView iFListaMascotasView, Context contexto) {
        this.iFListaMascotasView = iFListaMascotasView;
        this.contexto = contexto;
        obtenerMascotasJSON();
    }

    @Override
    public void obtenerMascotasJSON() {
        RestApiAdapter restApiAdapter=new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndPointsApi endPointsApi = restApiAdapter.EstablecerConexionRestApiInstagram(gsonMediaRecent);

        Call<MascotaJson> mascotaJsonCall=endPointsApi.getRecentMediaByUser(
                ConstantesRestApi.KEY_GET_RECENT_MEDIA_USER_1 +
                        ConfigurarCuenta.id_usuario_cuenta +
                        ConstantesRestApi.KEY_GET_RECENT_MEDIA_USER_2 +
                        ConstantesRestApi.KEY_ACCESS_TOKEN +
                        ConstantesRestApi.ACCESS_TOKEN );

        mascotaJsonCall.enqueue(new Callback<MascotaJson>() {
            @Override
            public void onResponse(Call<MascotaJson> call, Response<MascotaJson> response) {
                MascotaJson mascotaJson = response.body();
                mascotasRecientesDeserdenadas= mascotaJson.getListaJsonMascotas();
                OrdenarMascotasTiempo ordenarMascotasTiempo=new OrdenarMascotasTiempo(mascotasRecientesDeserdenadas);
                mascotasRecientesOrdenadas= ordenarMascotasTiempo.ordenar();
                MascotasTopFive mascotasTopFive=new MascotasTopFive();
                mascotasTop5 = mascotasTopFive.mascotas_top(mascotasRecientesOrdenadas);
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
        iFListaMascotasView.inicializarAdaptadorRV(iFListaMascotasView.crearAdaptador(mascotasTop5));
        iFListaMascotasView.generarLinearLayoutVertical();
    }
}
