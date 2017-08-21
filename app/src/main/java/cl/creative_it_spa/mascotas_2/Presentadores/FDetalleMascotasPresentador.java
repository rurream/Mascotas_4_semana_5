package cl.creative_it_spa.mascotas_2.Presentadores;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.ConfigurarCuenta;
import cl.creative_it_spa.mascotas_2.InterfazPresentador.IFDetalleMascotasPresentador;
import cl.creative_it_spa.mascotas_2.InterfazView.IFDetalleMascotasView;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
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

public class FDetalleMascotasPresentador implements IFDetalleMascotasPresentador {

    private IFDetalleMascotasView ifDetalleMascotasView;
    private Context contexto;
    private ArrayList<ListaMascotas> mascotasDesordenadas, mascotasOrdenadas;
    private String id_mascotaSeleccionada;

    public FDetalleMascotasPresentador(IFDetalleMascotasView ifDetalleMascotasView, Context contexto, String id_mascotaSeleccionada) {
        this.ifDetalleMascotasView = ifDetalleMascotasView;
        this.contexto = contexto;
        this.id_mascotaSeleccionada=id_mascotaSeleccionada;
        obtenerMascotaSeleccionadaInsta();
    }

    @Override
    public  void obtenerMascotaSeleccionadaInsta(){
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
                mascotasDesordenadas= mascotaJson.getListaJsonMascotas();
                OrdenarMascotasTiempo ordenarMascotasTiempo= new OrdenarMascotasTiempo(mascotasDesordenadas);
                mascotasOrdenadas = ordenarMascotasTiempo.ordenar();
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
        ifDetalleMascotasView.inicializarAdaptadorRV(ifDetalleMascotasView.crearAdaptador(mascotasOrdenadas, id_mascotaSeleccionada));
        ifDetalleMascotasView.generarGridLayoutVertical();
    }
}
