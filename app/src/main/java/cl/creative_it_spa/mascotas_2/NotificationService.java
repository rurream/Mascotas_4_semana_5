package cl.creative_it_spa.mascotas_2;

//import android.app.NotificationManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
//import android.support.v4.app.NotificationCompat;
import android.util.Log;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
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
 * Created by Rodrigo on 01-08-2017.
 */

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom() + "*****************");
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        String[] valoresDesdeHeroku=remoteMessage.getNotification().getBody().split("///p//");


        Intent intentPhone= new Intent(this, MainActivity.class);
        intentPhone.putExtra("mostrar_detalle", "si");
        intentPhone.putExtra("id_usuario_cuenta", valoresDesdeHeroku[0]);
        intentPhone.putExtra("mascotaLikeada", valoresDesdeHeroku[1]);
        PendingIntent pendingIntentPhone = PendingIntent.getActivity(this, 0, intentPhone, PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intentWear1 = new Intent();
        intentWear1.setAction("TOQUE_ANIMAL1");
        PendingIntent pendingIntentWear1 = PendingIntent.getBroadcast(this, 0, intentWear1, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentWear2 = new Intent();
        intentWear2.putExtra("id_usuario_cuenta", valoresDesdeHeroku[0]);
        intentWear2.setAction("TOQUE_ANIMAL2");
        PendingIntent pendingIntentWear2 = PendingIntent.getBroadcast(this, 0, intentWear2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentWear3 = new Intent();
        intentWear3.putExtra("id_usuario_cuenta", valoresDesdeHeroku[0]);
        intentWear3.setAction("TOQUE_ANIMAL3");
        PendingIntent pendingIntentWear3 = PendingIntent.getBroadcast(this, 0, intentWear3, PendingIntent.FLAG_UPDATE_CURRENT);



        NotificationCompat.Action action1=
                new NotificationCompat.Action.Builder(
                        R.drawable.ic_full_poke_web,
                        getString(R.string.texto_accion_toque_animal1),
                        pendingIntentWear1)
                        .build();

        NotificationCompat.Action action2 =
                new NotificationCompat.Action.Builder(
                        R.drawable.ic_full_poke_web,
                        getString(R.string.texto_accion_toque_animal2),
                        pendingIntentWear2)

                        .build();

        NotificationCompat.Action action3 =
                new NotificationCompat.Action.Builder(
                        R.drawable.ic_full_poke_web,
                        getString(R.string.texto_accion_toque_animal3),
                        pendingIntentWear3)
                        .build();



        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(),
                        R.drawable.bk_androidwear_notification))
                .setGravity(Gravity.CENTER_VERTICAL)
                .addAction(action1)
                .addAction(action2)
                .addAction(action3)
                ;




        NotificationCompat.Builder notificacion = new NotificationCompat
        .Builder(this)
        .setSmallIcon(R.drawable.icons8_megaphone_64)
        .setContentTitle("Se ha recibido un Like en Mascotas")
        .setContentText(valoresDesdeHeroku[2] + " te ha dado un LIKE.")
        .setSound(sonido)
        .setContentIntent(pendingIntentPhone)
        .extend(wearableExtender)
                .setAutoCancel(true)

        ;
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, notificacion.build());

    }




}
