package es.irunet.ardudroidcontrol;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentServiceLecturaDatos extends IntentService {
    //declaracion de acciones
    public static final String ACTION_DATO_READY = "es.irunet.ardudroidcontrol.action.DATO_READY";
    public static final String ACTION_READ_DATA = "es.irunet.ardudroidcontrol.action.READ_DATA";
    public static final String ACTION_END = "es.irunet.ardudroidcontrol.action.END";

    // Declaracion de parametros
    public static final String EXTRA_PARAM1 = "es.irunet.ardudroidcontrol.extra.PARAM1";

    public static int contador = 0;

    public IntentServiceLecturaDatos() {
        super("LecturaDatosIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionLeerDato(Context context, String param1) {
        Intent intent = new Intent(context, IntentServiceLecturaDatos.class);
        intent.setAction(ACTION_READ_DATA);
        intent.putExtra(EXTRA_PARAM1, param1);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_READ_DATA.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);

                String rta = handleActionLeerDato(param1);

                Intent bcIntent = new Intent();
                bcIntent.putExtra("DATO", rta);
                bcIntent.setAction(ACTION_DATO_READY);
                sendBroadcast(bcIntent);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     * Simulacion de la tarjeta
     */
    /*
    private String handleActionLeerDato_(String param1) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        contador++;
        return String.valueOf(contador);

    }
    */

    private String handleActionLeerDato(String param1) {
        String rta = "";
        try {
            //MainActivity.SendCommand(param1);
            //rta = MainActivity.ReadResponse();
            rta = MainActivity.SendCommandAndWaitResult(param1);
            return rta;
        } catch (Exception e) {
        }
        return rta;
    }


}
