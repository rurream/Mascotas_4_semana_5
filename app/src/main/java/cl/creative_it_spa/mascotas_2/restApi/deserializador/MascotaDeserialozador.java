package cl.creative_it_spa.mascotas_2.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cl.creative_it_spa.mascotas_2.POJOs.ListaMascotas;
import cl.creative_it_spa.mascotas_2.restApi.JsonKeys;
import cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram.MascotaJson;

/**
 * Created by Rodrigo on 20-07-2017.
 */

public class MascotaDeserialozador implements JsonDeserializer<MascotaJson> {

    @Override
    public MascotaJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        MascotaJson mascotaJson=gson.fromJson(json, MascotaJson.class);
        JsonArray mascotaJsonData=json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaJson.setListaJsonMascotas(deserializar(mascotaJsonData));
        return mascotaJson;
    }


    private ArrayList<ListaMascotas> deserializar(JsonArray mascotaJsonData){
        ArrayList<ListaMascotas> mascotasR=new ArrayList();
        for (int i = 0; i < mascotaJsonData.size(); i++) {
            String nombre_a_mostrar="";

            JsonObject mascotaResponseDataObject = mascotaJsonData.get(i).getAsJsonObject();
            String create_time=mascotaResponseDataObject.get(JsonKeys.FECHA_CREACION).getAsString();

            JsonObject userJson=mascotaResponseDataObject.getAsJsonObject(JsonKeys.LLAVE_USER);

            String nombre_mascota_caption="";
            if (! mascotaResponseDataObject.get(JsonKeys.LLAVE_CAPTION).isJsonNull()){
                JsonObject captionJson=mascotaResponseDataObject.getAsJsonObject(JsonKeys.LLAVE_CAPTION);
                nombre_mascota_caption = captionJson.get(JsonKeys.MASCOTA_NOMBRE).getAsString();
            }

            String nombre_completo = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            if (nombre_mascota_caption==""){
                nombre_a_mostrar=nombre_completo;
            } else {
                nombre_a_mostrar=nombre_mascota_caption;
            }

            JsonObject imageJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.LLAVE_IMAGES);
            JsonObject std_res = imageJson.getAsJsonObject(JsonKeys.LLAVE_STANDARD_RESOLUTION);
            String url_imagen = std_res.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson=mascotaResponseDataObject.getAsJsonObject(JsonKeys.LLAVE_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES).getAsInt();


            String id_foto=mascotaResponseDataObject.get(JsonKeys.FOTO_ID).getAsString();



            ListaMascotas row = new ListaMascotas();
            row.setId(id_foto);
            row.setNombre_mascota(nombre_a_mostrar);
            row.setFoto_mascota(url_imagen);
            row.setPuntaje_mascota(likes);
            row.setFecha_creacion(create_time);
            row.setFullName(nombre_completo);

            mascotasR.add(row);
        }
        return mascotasR;
    }
}
