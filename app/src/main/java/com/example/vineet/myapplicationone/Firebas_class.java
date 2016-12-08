package com.example.vineet.myapplicationone;

import android.util.JsonToken;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Vineet on 11/23/2016.
 */

public class Firebas_class extends FirebaseInstanceIdService {
    private static final String TAG = "FCMIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
//        try {
//            String refreshedToken = FirebaseInstanceId.getInstance().subscribetotopic("fango_orders");
//            Log.d("it is", "Refreshed token: -----------" + refreshedToken);
//
//        }
//        catch (Exception e){
//            Log.d("error ", "is "+e);
//        }
//        sendRegistrationToServer();
//
//    }

        // TODO: Implement this method to send any registration to your app's servers.
//        private void sendRegistrationToServer( String token ) {
            try {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                Log.d("it is", "Refreshed token: -----------" + refreshedToken);

            }
            catch (Exception e){
                Log.d("error ", "is "+e);
            }

        }

}
