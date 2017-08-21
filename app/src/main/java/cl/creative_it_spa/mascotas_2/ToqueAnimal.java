package cl.creative_it_spa.mascotas_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ConsultaFollow;
import cl.creative_it_spa.mascotas_2.restApi.Adapter.RestApiAdapter;
import cl.creative_it_spa.mascotas_2.restApi.ConstantesRestApi;
import cl.creative_it_spa.mascotas_2.restApi.EndPointsApi;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.ConsultaFollowJson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rodrigo on 13-08-2017.
 */

public class ToqueAnimal extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {

        //•	Ver mi Perfil (abrirá la aplicación del teléfono en la pantalla de home del usuario establecido)
        if (intent.getAction().equals("TOQUE_ANIMAL1")){
            Log.d("Activacion de toque : ", " CORRECTA 1");

            Intent intentPhone= new Intent(context, MainActivity.class);
            intentPhone.putExtra("mostrar_detalle", "no");
            intentPhone.putExtra("id_usuario_cuenta", "5750131561");
            intentPhone.putExtra("mascotaLikeada", "1");
            intentPhone.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentPhone);


        //•	Dar Follow/Unfollow (a la persona que raiteo tu foto, implementar Endpoint)
        } else if (intent.getAction().equals("TOQUE_ANIMAL2")){
            Log.d("Activacion de toque : ", " CORRECTA 2");
            Bundle extras = intent.getExtras();
            String id_usuario_cuenta_da_like= extras.getString("id_usuario_cuenta");
            consultarFollow(id_usuario_cuenta_da_like, context);


        //•	Ver Usuario (Abrirá un nuevo activity en la aplicación del teléfono en el que
            // se visualizarán las fotos recientes del usuario que raiteo la foto)
        } else if (intent.getAction().equals("TOQUE_ANIMAL3")) {
            Log.d("Activacion de toque : ", " CORRECTA 3");

            Bundle extras = intent.getExtras();
            String id_usuario_cuenta= extras.getString("id_usuario_cuenta");
            Intent intentPhone= new Intent(context, MascotasUsuarioDioLike.class);
            intentPhone.putExtra("id_usuario_cuenta", id_usuario_cuenta);
            intentPhone.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentPhone);
        }
    }



    public void consultarFollow(final String id_usuario_cuenta_da_like, final Context context){
        RestApiAdapter restApiAdapter=new RestApiAdapter();
        Gson gsonFollow = restApiAdapter.construyeGsonDeserializadorConsultaFollow();
        EndPointsApi endPointsApi = restApiAdapter.EstablecerConexionRestApiInstagram(gsonFollow);
        Call<ConsultaFollowJson> consultaFollowJsonCall=endPointsApi.consultarFollow(
                ConstantesRestApi.KEY_GET_RECENT_MEDIA_USER_1 +
                        id_usuario_cuenta_da_like +
                        ConstantesRestApi.KEY_Relationship +
                        ConstantesRestApi.KEY_ACCESS_TOKEN +
                        ConstantesRestApi.ACCESS_TOKEN);

        consultaFollowJsonCall.enqueue(new Callback<ConsultaFollowJson>() {
            @Override
            public void onResponse(Call<ConsultaFollowJson> call, Response<ConsultaFollowJson> response) {
                ConsultaFollowJson consultaFollowJson = response.body();
                ArrayList<ConsultaFollow> respuesta = consultaFollowJson.getConsultaFollows();
                Toast.makeText(context, "El estado inicial es:  " + respuesta.get(0).getEstado_follow(), Toast.LENGTH_SHORT).show();
                String action="follow";
                Log.d("Estado inicial: ", respuesta.get(0).getEstado_follow());
                if (respuesta.get(0).getEstado_follow().equals("none") || respuesta.get(0).getEstado_follow().equals("requested")){
                    action="follow";
                    Log.d("Pasa por 1 : ", "Action= " + action);
                } else {
                    action="unfollow";
                    Log.d("Pasa por 2 : ", "Action= " + action);
                }
                followUnFollow(id_usuario_cuenta_da_like, action, context);
            }

            @Override
            public void onFailure(Call<ConsultaFollowJson> call, Throwable t) {
                Log.d("Respuesta a FOLLOW: ", " ERROR");
            }
        });
    }




    public void followUnFollow(String id_usuario_cuenta_da_like, String action, final Context context){
        RestApiAdapter restApiAdapter=new RestApiAdapter();
        Gson gsonFollow = restApiAdapter.construyeGsonDeserializadorConsultaFollow();
        EndPointsApi endPointsApi = restApiAdapter.EstablecerConexionRestApiInstagram(gsonFollow);
        Call<ConsultaFollowJson> consultaFollowJsonCall=endPointsApi.modificarFollow(
                ConstantesRestApi.KEY_GET_RECENT_MEDIA_USER_1 +
                        id_usuario_cuenta_da_like +
                        ConstantesRestApi.KEY_Relationship +
                        ConstantesRestApi.KEY_ACCESS_TOKEN +
                        ConstantesRestApi.ACCESS_TOKEN, action);

        consultaFollowJsonCall.enqueue(new Callback<ConsultaFollowJson>() {
            @Override
            public void onResponse(Call<ConsultaFollowJson> call, Response<ConsultaFollowJson> response) {
                ConsultaFollowJson consultaFollowJson = response.body();
                ArrayList<ConsultaFollow> respuesta = consultaFollowJson.getConsultaFollows();
                Toast.makeText(context, "Ahora el estado es:  " +
                        respuesta.get(0).getEstado_follow(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ConsultaFollowJson> call, Throwable t) {
                Log.d("Modifica FOLLOW : ", "ERROR");
            }
        });


    }


}
