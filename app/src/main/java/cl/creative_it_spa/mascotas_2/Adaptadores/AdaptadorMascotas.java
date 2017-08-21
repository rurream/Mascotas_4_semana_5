package cl.creative_it_spa.mascotas_2.Adaptadores;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.ConfigurarCuenta;
import cl.creative_it_spa.mascotas_2.Fragments.FDetalleMascotas;
import cl.creative_it_spa.mascotas_2.MainActivity;
import cl.creative_it_spa.mascotas_2.POJOs.LikeMascota;
import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
import cl.creative_it_spa.mascotas_2.POJOs.OrdenarMascotasTiempo;
import cl.creative_it_spa.mascotas_2.POJOs.RegistroFireBase;
import cl.creative_it_spa.mascotas_2.R;
import cl.creative_it_spa.mascotas_2.restApi.Adapter.RestApiAdapter;
import cl.creative_it_spa.mascotas_2.restApi.ConstantesRestApi;
import cl.creative_it_spa.mascotas_2.restApi.EndPointsApi;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.DarLikeFB;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.DarLikeJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.FireBaseJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.MascotaJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.UsuarioResponse2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rodrigo on 13-06-2017.
 */

public class AdaptadorMascotas extends RecyclerView.Adapter<AdaptadorMascotas.MascotasViewHolder>{

    ArrayList<ListaMascotas> mascotaOrdenada, mascotasDesordenadas;
    Activity activity;
    String idMascotaSeleccionada="1";
    String idMascotaLike="";
    ArrayList<LikeMascota> likeMascotas;
    String idFireBase ="";

    public AdaptadorMascotas(ArrayList<ListaMascotas> mascotas, Activity activity) {
        this.mascotasDesordenadas = mascotas;
        this.activity = activity;
        ordenar();
    }

    private void ordenar(){
        OrdenarMascotasTiempo ordenarMascotasTiempo= new OrdenarMascotasTiempo(mascotasDesordenadas);
        mascotaOrdenada = ordenarMascotasTiempo.ordenar();
    }

    @Override
    public MascotasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);
        return new MascotasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotasViewHolder holder_de_mascotas, final int position) {
        final ListaMascotas mascotax= mascotaOrdenada.get(position);

        Picasso.with(activity)
                .load(mascotax.getFoto_mascota())
                .placeholder(R.drawable.default_dog)
                .into(holder_de_mascotas.img_foto_mascota);

        holder_de_mascotas.tv_nombre_mascota.setText(mascotax.getNombre_mascota());
        holder_de_mascotas.tv_puntos_mascota.setText("" + mascotax.getPuntaje_mascota());

        idMascotaSeleccionada = MainActivity.mascotaLikeada;

       if (idMascotaSeleccionada == "1"){
           idMascotaSeleccionada = mascotaOrdenada.get(0).getId();
       }
        if(idMascotaSeleccionada == mascotax.getId()){
            holder_de_mascotas.cvMascotas.setBackgroundColor(activity.getResources().getColor(R.color.colorSeleccion));
        }else{
            holder_de_mascotas.cvMascotas.setBackgroundColor(activity.getResources().getColor(R.color.colorBlanco));
        }



        holder_de_mascotas.img_hueso_blanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.playSoundEffect(android.view.SoundEffectConstants.CLICK);

                idMascotaLike=mascotax.getId();

                //Consultar id de registro en FIreBase que tiene guardado el id de Instagram
                //del usuario Instagram que acaba de dar Like --> PARA RECUPERAR EL ID_REGISTRO FIREBASE
                RestApiAdapter restApiAdapterFB=new RestApiAdapter();
                Gson gsRespuestaFB = restApiAdapterFB.construyrDeserializadorRespuestaFireBase();
                EndPointsApi endPointsApiFB = restApiAdapterFB. EstablecerConexionRestApiFB_Token(gsRespuestaFB);
                Call<FireBaseJson> FBJsonCall = endPointsApiFB.findToken();
                FBJsonCall.enqueue(new Callback<FireBaseJson>() {
                    @Override
                    public void onResponse(Call<FireBaseJson> call, Response<FireBaseJson> response) {
                        FireBaseJson fireBaseJson = response.body();
                        ArrayList<RegistroFireBase> respuestaFBData;
                        respuestaFBData= fireBaseJson.getRespuestaFB();

                        for (int i = 0; i < respuestaFBData.size(); i++) {
                            Log.d("Encontrado ID Firebase" + i + " : ", respuestaFBData.get(i).getId_firebase());
                            Log.d("Encontrado Token" + i + " : ", respuestaFBData.get(i).getToken());
                            Log.d("Encontrado id_usuario_INSTAGRAM" + i + " : ", respuestaFBData.get(i).getId_usuario_instagram());
                        }
                        idFireBase = respuestaFBData.get(0).getId_firebase();
                        toqueAnimal(idFireBase, idMascotaLike);
                        guardarLikesFireBse();
                    }

                    @Override
                    public void onFailure(Call<FireBaseJson> call, Throwable t) {
                        Log.d("XXXXXXXX Problemas... " , t.getMessage());
                    }
                });






















                //darLike ********************************

                RestApiAdapter restApiAdapter2=new RestApiAdapter();
                Gson gsonLike = restApiAdapter2.construyrDeserializadorLikes();
                EndPointsApi endPointsApi = restApiAdapter2.EstablecerConexionRestApiInstagram(gsonLike);
                Call<DarLikeJson> likeJsonCall2= endPointsApi.sendLike2(ConstantesRestApi.KEY_SEND_LIKE_1 +
                        idMascotaLike +
                        ConstantesRestApi.KEY_SEND_LIKE_2, ConstantesRestApi.ACCESS_TOKEN);

                likeJsonCall2.enqueue(new Callback<DarLikeJson>() {
                    @Override
                    public void onResponse(Call<DarLikeJson> call, Response<DarLikeJson> response) {
                        DarLikeJson darLikeJson= response.body();
                        likeMascotas = darLikeJson.getLikes_mascota();

                        if (likeMascotas.size()>0){








                            //Actualizar número de Likes *********************************************

                            RestApiAdapter restApiAdapter3=new RestApiAdapter();
                            Gson gsonMediaRecent2 = restApiAdapter3.construyeGsonDeserializadorMediaRecent();
                            EndPointsApi endPointsApi2 = restApiAdapter3.EstablecerConexionRestApiInstagram(gsonMediaRecent2);
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
                                    ArrayList<ListaMascotas> listaActualizadaMascotas;
                                    listaActualizadaMascotas= mascotaJson.getListaJsonMascotas();

                                    int puntajeActualizado=0;
                                    for (int i = 0; i < listaActualizadaMascotas.size(); i++) {
                                        ListaMascotas mascotaAnalizada = listaActualizadaMascotas.get(i);

                                        if (idMascotaLike.equals(mascotaAnalizada.getId())){
                                            puntajeActualizado= mascotaAnalizada.getPuntaje_mascota();
                                            break;
                                        }
                                    }

                                    for (int j = 0; j < mascotaOrdenada.size(); j++) {
                                        ListaMascotas mascotaAnalizada = mascotaOrdenada.get(j);
                                        if (idMascotaLike.equals(mascotaAnalizada.getId())){
                                            mascotaOrdenada.get(j).setPuntaje_mascota(puntajeActualizado);
                                            notifyDataSetChanged();
                                            Toast.makeText(activity, "OK, haz dado like a " +
                                                    mascotaOrdenada.get(j).getNombre_mascota(), Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MascotaJson> call, Throwable t) {
                                    //implementar algo por si falla
                                    Log.e("FALLO LA CONEXION", t.toString());
                                }
                            });









                        } else {
                    Toast.makeText(activity, "FALLO LA CONEXION", Toast.LENGTH_SHORT).show();
                }
                    }

                    @Override
                    public void onFailure(Call<DarLikeJson> call, Throwable t) {
                        Log.e("FALLO LA CONEXION *****", t.toString());
                    }
                });

            }
        });

        holder_de_mascotas.img_foto_mascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.playSoundEffect(android.view.SoundEffectConstants.CLICK);

                MainActivity.mascotaLikeada = mascotax.getId();
                idMascotaSeleccionada = mascotax.getId();

                FDetalleMascotas fragmento= new FDetalleMascotas(idMascotaSeleccionada);
                FragmentTransaction transaccion= ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
                transaccion.replace(R.id.ly_mascota_seleccionada, fragmento);
                transaccion.addToBackStack(null);
                transaccion.commit();

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotaOrdenada.size();
    }





    public static class MascotasViewHolder extends RecyclerView.ViewHolder{

        ImageView img_foto_mascota, img_hueso_color;
        TextView tv_nombre_mascota;
        TextView tv_puntos_mascota;
        ImageButton img_hueso_blanco;
        CardView cvMascotas;

        public MascotasViewHolder(View itemView) {
            super(itemView);

            img_foto_mascota=(ImageView) itemView.findViewById(R.id.img_foto_mascota);
            tv_nombre_mascota=(TextView) itemView.findViewById(R.id.tv_nombre_mascota);
            tv_puntos_mascota=(TextView) itemView.findViewById(R.id.tv_puntos_mascota);
            img_hueso_blanco=(ImageButton) itemView.findViewById(R.id.img_hueso_blanco);
            img_hueso_color=(ImageView) itemView.findViewById(R.id.img_hueso_color);

            cvMascotas=(CardView) itemView.findViewById(R.id.cvMascotas);
        }
    }


    //Se consulta el token-device del usuario de la cuenta Instagram asociado a la foto a la cual se dio Like
    //Este token-device está en nuestra base de datos de FireBase
    //El mismo EndPoint (almacenado en servidor Web Heroku) envía Notificación
    public void toqueAnimal(String idBaseDatos, String id_foto_mascota){

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.EstablecerConexionRestApiFB();
        Call<UsuarioResponse2> usuarioResponse2Call = endPointsApi.toqueAnimal(idBaseDatos,
                id_foto_mascota, ConfigurarCuenta.nombre_usuario_cuenta);

        usuarioResponse2Call.enqueue(new Callback<UsuarioResponse2>() {
            @Override
            public void onResponse(Call<UsuarioResponse2> call, Response<UsuarioResponse2> response) {
                UsuarioResponse2 usuarioResponse2 = response.body();
                Log.d("ID_FirBase: ", usuarioResponse2.getId_firebase());
                Log.d("Token: ", usuarioResponse2.getToken());
                Log.d("Id_Usuario_Instagram: ", usuarioResponse2.getId_usuario_instagram());
                Log.d("Id_Foto_Instagram: ", usuarioResponse2.getId_foto_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse2> call, Throwable t) {
                Log.e("FALLO El Envio de Like ", t.toString());
            }
        });

    }


    //Guardar registro del Like en Firebase
    //Se guarda el Usuario de Instagram de la cuenta que tiene la mascota
    //El id de la mascota a la que se ha dado Like
    //El token-device desde el cual se ha dado este Like
    public void guardarLikesFireBse(){

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        RestApiAdapter restApiAdapterFBLike=new RestApiAdapter();
        EndPointsApi endPointsApiFBLike = restApiAdapterFBLike. EstablecerConexionRestApiFB();
        Call<DarLikeFB> LikeFBJsonCall = endPointsApiFBLike.darLikeMascota(ConfigurarCuenta.id_usuario_cuenta,
                idMascotaLike, refreshedToken);
        LikeFBJsonCall.enqueue(new Callback<DarLikeFB>() {
            @Override
            public void onResponse(Call<DarLikeFB> call, Response<DarLikeFB> response) {
                DarLikeFB darLikeFB = response.body();

                Log.d("Like Insertado en FB: ", darLikeFB.getLike_mascota());
            }

            @Override
            public void onFailure(Call<DarLikeFB> call, Throwable t) {
                Log.d("Problemas Insertar Like" , t.getMessage());
            }
        });
    }
}
