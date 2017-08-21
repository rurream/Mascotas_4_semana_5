package cl.creative_it_spa.mascotas_2.restApi;

import cl.creative_it_spa.mascotas_2.ConfigurarCuenta;

/**
 * Created by Rodrigo on 19-07-2017.
 */

public final class ConstantesRestApi {


    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5750131561.ba41dea.bed4a568cfd349c3be9c5d5e1e66bcc1";
    public static final String KEY_ACCESS_TOKEN = "access_token=";

    public static final String KEY_GET_RECENT_MEDIA_USER_1 = "users/";
    public static final String KEY_GET_RECENT_MEDIA_USER_2 = "/media/recent/?";
    //public static final String KEY_GET_RECENT_MEDIA_USER = "users/5750131561/media/recent/?";
    //public static final String URL_GET_RECENT_MEDIA_BY_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN



    public static final String KEY_GET_USER_DATA = "users/search?q=";
    public static final String KEY_ampesand = "&";
    public static final String URL_USER_DATA_BY_USER_NAME = KEY_GET_USER_DATA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN

    public static final String KEY_SEND_LIKE_1 = "media/";
    public static final String KEY_SEND_LIKE_2 = "/likes";
    public static final String prueba = "media/1563948004493285751_287695527/likes";
    public static final String curl = "access_token=5750131561.ba41dea.bed4a568cfd349c3be9c5d5e1e66bcc1";

    //https://api.instagram.com/v1/media/{media-id}/likes




    public static final String ROOT_URL_FB = "https://intense-temple-60504.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "token-device/";
    public static final String KEY_POST_ID_TOKEN_ID_USUARIO_INSTAGRAM = "registrar-usuario/";
    public static final String KEY_TOQUE_ANIMAL = "toque-animal/{id_firebase}/{id_foto_instagram}/{fullName}/";

    public static final String KEY_GET_ID_FIREBASE = "consulta-id-firebase/";
    public static final String KEY_POST_DAR_LIKE = "registrar-like/";


    //Consultar relación de FOLLOW
    //https://api.instagram.com/v1/users/{user-id}/relationship?access_token=ACCESS-TOKEN
    public static final String KEY_Relationship = "/relationship?";


    //Modificar relación de FOLLOW
    //https://api.instagram.com/v1/users/{user-id}/relationship?access_token=ACCESS-TOKEN
}
