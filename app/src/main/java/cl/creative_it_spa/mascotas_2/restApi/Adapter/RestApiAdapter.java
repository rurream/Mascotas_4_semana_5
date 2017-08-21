package cl.creative_it_spa.mascotas_2.restApi.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cl.creative_it_spa.mascotas_2.ConfigurarCuenta;
import cl.creative_it_spa.mascotas_2.restApi.ConstantesRestApi;
import cl.creative_it_spa.mascotas_2.restApi.EndPointsApi;
import cl.creative_it_spa.mascotas_2.restApi.deserializador.ConsultaFollowDeserializador;
import cl.creative_it_spa.mascotas_2.restApi.deserializador.FindTokenDeserializador;
import cl.creative_it_spa.mascotas_2.restApi.deserializador.LikeDeserializador;
import cl.creative_it_spa.mascotas_2.restApi.deserializador.MascotaDeserialozador;
import cl.creative_it_spa.mascotas_2.restApi.deserializador.UsuarioDeserializador;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.ConsultaFollowJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.DarLikeJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.FireBaseJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.MascotaJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.UsuarioJson;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rodrigo on 19-07-2017.
 */

public class RestApiAdapter {


    public EndPointsApi EstablecerConexionRestApiInstagram(Gson gson){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaJson.class, new MascotaDeserialozador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUsuarios(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioJson.class, new UsuarioDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyrDeserializadorLikes(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DarLikeJson.class, new LikeDeserializador());

        return gsonBuilder.create();
    }


    public Gson construyeGsonDeserializadorConsultaFollow(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ConsultaFollowJson.class, new ConsultaFollowDeserializador());

        return gsonBuilder.create();
    }


    public Gson construyrDeserializadorRespuestaFireBase(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FireBaseJson.class, new FindTokenDeserializador());

        return gsonBuilder.create();
    }


    public EndPointsApi EstablecerConexionRestApiFB(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_FB)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(EndPointsApi.class);
    }

    public EndPointsApi EstablecerConexionRestApiFB_Token(Gson gson){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_FB)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsApi.class);
    }
}
