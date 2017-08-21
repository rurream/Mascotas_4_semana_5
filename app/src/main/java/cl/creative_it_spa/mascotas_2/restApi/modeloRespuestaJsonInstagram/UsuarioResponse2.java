package cl.creative_it_spa.mascotas_2.restApi.modeloRespuestaJsonInstagram;

/**
 * Created by Rodrigo on 07-08-2017.
 */

public class UsuarioResponse2 {

    private String id_firebase;
    private  String token;
    private String id_usuario_instagram;
    private String id_foto_instagram;

    public UsuarioResponse2(String id_firebase, String token, String id_usuario_instagram, String id_foto_instagram) {
        this.id_firebase = id_firebase;
        this.token = token;
        this.id_usuario_instagram = id_usuario_instagram;
        this.id_foto_instagram = id_foto_instagram;
    }

    public UsuarioResponse2() {
    }

    public String getId_firebase() {
        return id_firebase;
    }

    public void setId_firebase(String id_firebase) {
        this.id_firebase = id_firebase;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(String id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }
}
