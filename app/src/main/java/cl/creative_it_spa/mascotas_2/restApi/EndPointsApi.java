package cl.creative_it_spa.mascotas_2.restApi;

import cl.creative_it_spa.mascotas_2.ConfigurarCuenta;
import cl.creative_it_spa.mascotas_2.POJOs.ConsultaFollow;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.ConsultaFollowJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.DarLikeFB;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.DarLikeJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.FireBaseJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.MascotaJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.UsuarioJson;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.UsuarioResponse;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.UsuarioResponse2;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Rodrigo on 19-07-2017.
 */

public interface EndPointsApi {

    @GET
    Call<UsuarioJson> getDataByUserName(@Url String url);

    @GET
    Call<MascotaJson> getRecentMediaByUser(@Url String url);


    @FormUrlEncoded
    @POST()
    Call<DarLikeJson> sendLike2(@Url String url, @Field("access_token") String token);


    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenID(@Field("token") String token);


    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN_ID_USUARIO_INSTAGRAM)
    Call<UsuarioResponse> registrarTokenIDTarea(@Field("token") String token,
                                                @Field("id_usuario_instagram") String id_usuario_instagram);


    @GET(ConstantesRestApi.KEY_TOQUE_ANIMAL)
    Call<UsuarioResponse2> toqueAnimal(@Path("id_firebase") String id_firebase,
                                       @Path("id_foto_instagram") String id_foto_instagram,
                                       @Path("fullName") String fullName);


    @GET(ConstantesRestApi.KEY_GET_ID_FIREBASE)
    Call<FireBaseJson> findToken();


    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_DAR_LIKE)
    Call<DarLikeFB> darLikeMascota(@Field("id_usuario_instagram") String id_usuario_instagram,
                                   @Field("id_foto_instagram") String id_foto_instagram,
                                   @Field("token") String token);



    @GET()
    Call<ConsultaFollowJson> consultarFollow(@Url String url);


    @FormUrlEncoded
    @POST()
    Call<ConsultaFollowJson> modificarFollow(@Url String url,
                                             @Field("action") String action);
}
